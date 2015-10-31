import static co.Constant.*; 
import java.awt.image.BufferedImage; 
import java.awt.event.InputEvent;
import java.awt.MouseInfo; 
import java.awt.Point; 
import java.util.HashMap; 
class TypeParseRobot {
    //int[] deltaH=new int[3];
    //int[] deltaV=new int[3]; 
    int currDepth=1;
    boolean incDepth=false;
    boolean xInput=true;
    int xCursor=0;
    int yCursor=0; 
    int[] xPos=new int[maxDepth]; 
    int[] yPos=new int[maxDepth]; 
    boolean mousePress=false; 
    boolean wheelPress=false; 
    boolean switchFocus=false; 
    HashMap<Character,Integer> horzSymb=new HashMap<Character,Integer>();
    HashMap<Character,Integer> vertSymb=new HashMap<Character,Integer>();

        
    RobotWrapper robot = new RobotWrapper();
    
    TprListener tprListener; 
    LetterListener ltrListener;
    public TypeParseRobot(TprListener t) //throws AWTException
    {
        for(int i=0;i<deltaH.length;i++){
            //this.deltaV[i]=globHeight/(int)Math.pow(div-1,i+1);
            //this.deltaH[i]=globWidth/(int)Math.pow(div-1,i+1);
   
            deltaV[i]=globHeight/(int)Math.pow(div-1,i+1);
            deltaH[i]=globWidth/(int)Math.pow(div-1,i+1);
            if (deltaV[i]==0){
                deltaV[i]=1;
            } else if (deltaH[i]==0){
                deltaH[i]=1;
            }
            //if (this.deltaV[i]==0){
                //this.deltaV[i]=1;
            //} else if (this.deltaH[i]==0){
                //this.deltaH[i]=1;
            //}

        }
        for(int i=0;i<symLoc.length;i++){
            horzSymb.put(symLoc[i],i);
            vertSymb.put(symLoc[i],i);
        }
        this.tprListener=t; 
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.xCursor=p.x;
        this.yCursor=p.y; 

        //initialize xPos yPos
        for(int i=0;i<maxDepth;i++){
            xPos[i]=0; 
            yPos[i]=0; 
        }
    }
    public void setTprListener(TprListener t){
        this.tprListener=t;
    }
     public void addLtrListener(LetterListener t){
        this.ltrListener=t;
    }

    public void recInput(char keyIn){
        if(horzSymb.containsKey(keyIn)){
            //goto location click
            locClick(keyIn); 
        } else if( keyIn==leftClick || keyIn==rightClick || keyIn==holdRelClick ||
            keyIn==wheelClick){
            //go to click 
            lrClick(keyIn); 
        } else if(keyIn==depthBack || keyIn==depthForward){
            depFunc(keyIn);  
        } else if(keyIn==changeFocus){
            focFunc(); 
        } else {
            dirClick(keyIn,currDepth); 
            //go to direction click
        }
    }
    public void locClick(char keyIn ){
      if(wheelPress){
           int sizeHashhalf=vertSymb.size()/2; 
           int wdown=vertSymb.get(keyIn)*deltaV[currDepth-1]-sizeHashhalf*deltaV[currDepth-1]; 
           //System.out.println("wheel ="+wdown); 
           robot.mouseWheel(wdown); 
           return;
      }
      if(xInput){
            //int temp=0; 
            //for(int i=0;i<currDepth;i++){
                //temp+=horzSymb.get(keyIn)*deltaH[currDepth-1-i]; 
                //xCursor=horzSymb.get(keyIn)*deltaH[currDepth-1]+paddingX;
            //}
            xPos[currDepth-1]=horzSymb.get(keyIn)*deltaH[currDepth-1];
            //xCursor=temp+paddingX;
            System.out.println(horzSymb.get(keyIn));
            System.out.println("pos X="+xCursor);
            System.out.println("deltaH"+deltaH[currDepth-1]);
            xInput=false; 
            tprListener.eraseListener();
            tprListener.typeListener(horzSymb.get(keyIn),true);
            tprListener.posListener(xPos,yPos); 
       } else if(xInput==false){
            //int temp=0; 
            //for(int i=0;i<currDepth;i++){
                //temp+=vertSymb.get(keyIn)*deltaV[currDepth-1-i]; 
                //xCursor=horzSymb.get(keyIn)*deltaH[currDepth-1]+paddingX;
            //}
            yPos[currDepth-1]=vertSymb.get(keyIn)*deltaV[currDepth-1];
            //yCursor=temp+paddingY;
            //yCursor=vertSymb.get(keyIn)*deltaV[currDepth-1]+paddingY ;
            xInput=true; 
            System.out.println("pos Y="+yCursor);
            tprListener.typeListener(vertSymb.get(keyIn),false); 
            tprListener.posListener(xPos,yPos); 
       }
            int totalX=0;
            int totalY=0; 
            for(int i=0;i<currDepth;i++){
                totalX+=xPos[i]+paddingX[i];
                totalY+=yPos[i]+paddingY[i];
            }
            xCursor=totalX; 
            yCursor=totalY; 
            //robot.mouseMove(xCursor,yCursor); 
            robot.mouseMove(xCursor,yCursor); 
    }
    public void dirClick(char keyIn, int depth){
       int xdirCurs=xCursor;
       int ydirCurs=yCursor;
       if(keyIn==dirSymb[0]){
          xdirCurs-=deltaH[depth-1];
       }else if(keyIn==dirSymb[1]){
          //System.out.println('j'); 
          ydirCurs+=deltaV[depth-1];
       }else if(keyIn==dirSymb[2]){
          ydirCurs-=deltaV[depth-1];
       }else if(keyIn==dirSymb[3]){
          xdirCurs+=deltaH[depth-1];
       }
       xCursor=xdirCurs;
       yCursor=ydirCurs; 
       robot.mouseMove(xdirCurs,ydirCurs); 
       //System.out.println("dir x="+xdirCurs);
       //System.out.println("dir y="+ydirCurs);
       tprListener.eraseListener();
   }
   public void lrClick(char keyIn){
    if(keyIn==leftClick){
        robot.mouseLeftClick(); 
        if(!switchFocus){
            robot.switchFocus(); 
        }
    } else if(keyIn==rightClick){
        robot.mouseRightClick(); 
    } else if(keyIn==holdRelClick){
        if(mousePress){
            robot.mouseLeftRelease(); 
            mousePress=false; 
        } else{
            robot.mouseLeftPress(); 
            mousePress=true; 
        }
    } else if(keyIn==wheelClick && wheelPress==false){
        wheelPress=true; 
    } else if(keyIn==wheelClick && wheelPress==true){
        wheelPress=false; 
    }
    tprListener.eraseListener();  
    }
    public void defFunc(){
        this.incDepth=false;
        this.xInput=true;
        this.xCursor=0;
        this.yCursor=0; 
        this.currDepth=1; 
    }
    public void depFunc(char keyIn){
        if(currDepth>=maxDepth){
            currDepth=maxDepth; 
        }
        if(keyIn==depthForward){
            currDepth++; 
        } else if(keyIn==depthBack){
            currDepth--; 
            if(currDepth<minDepth){
                currDepth=minDepth; 
            }
            else{
                xPos[currDepth]=0; 
                yPos[currDepth]=0; 
            }
        }

        //Capturing screen
        BufferedImage bf; 
        if(currDepth==minDepth){
            //System.out.println("bf is minDepth"); 
            //bf=robot.captureScr(0,0,globWidth,globHeight); 
            bf=null; 
        } else{
            //System.out.println("bf is "+currDepth); 
            //bf=robot.captureScr(xCursor,yCursor,deltaH[currDepth-2],deltaV[currDepth-2]); 
            bf=capScr(); 
        }
        tprListener.imageListener(bf,currDepth); 
    }
    public BufferedImage capScr(){
        if(currDepth==minDepth){
            return null; 
        }
        return robot.captureScr(xPos[currDepth-2],yPos[currDepth-2],deltaH[currDepth-2],deltaV[currDepth-2]); 
    }
    
    private void focFunc(){
        if (switchFocus==true){
            this.switchFocus=false;
        }else if (switchFocus==false){
            this.switchFocus=true;
        }
    }
    public void delayWait(int i){
    try {
          Thread.sleep(i);
    } catch (InterruptedException ie) {
        }
    }
    public int getXCursor(){
        return xCursor; 
    }
    public int getYCursor(){
        return yCursor; 
    }
}
