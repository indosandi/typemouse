//import java.awt.Robot;
//import static Constant.*; 
 //import static MyValues.*; 
//import static Constant.*; 
package org.indosandi.typemouse; 
import static co.Constant.*; 
import java.awt.event.InputEvent;
import java.awt.MouseInfo; 
import java.awt.Point; 
//import java.awt.AWTException;
//import java.awt.event.KeyEvent;
import java.util.HashMap; 
class TypeParseRobot {
    //int globHeight=900;
    //int globWidth=1440;
    int[] deltaH=new int[3];
    int[] deltaV=new int[3]; 
    int currDepth=1;
    boolean incDepth=false;
    boolean xInput=true;
    int xCursor=0;
    int yCursor=0; 
    boolean mousePress=false; 
    boolean wheelPress=false; 
    boolean switchFocus=false; 
    HashMap<Character,Integer> horzSymb=new HashMap<Character,Integer>();
    HashMap<Character,Integer> vertSymb=new HashMap<Character,Integer>();

    //char[] symLoc={'a','s','d','f','w','e','x','c','v'}; 
    //char leftClick='t';
    //char holdRelClick='r';
    //char rightClick='g';
    //char wheelClick='u';
    //char[] dirSymb={'h','j','k','l'}; 
    //char depthBack='i'; 
    //char depthForward='o'; 
    //char changeFocus='q'; 
    
    RobotWrapper robot = new RobotWrapper();
    //public static void  main(String[] args) throws AWTException{
        //TypeParseRobot tpr=new TypeParseRobot(); 
        //int iWait=1000; 
        //tpr.recInput('w'); 
        //tpr.recInput('w'); 
        //tpr.delayWait(iWait); 
        //tpr.recInput('u'); 
        //tpr.recInput('f'); 
        //tpr.delayWait(iWait); 
        //tpr.recInput('v'); 
        //tpr.recInput('u'); 
        //tpr.delayWait(iWait); 
        //tpr.recInput('j'); 
        //tpr.delayWait(iWait); 
        //tpr.recInput('h'); 
        //return; 
            //}

    TprListener tprListener; 
    //LetterListener ltrListener;
    public TypeParseRobot(TprListener t) //throws AWTException
    {
        for(int i=0;i<deltaH.length;i++){
            this.deltaV[i]=globHeight/(int)Math.pow(div-1,i+1);
            this.deltaH[i]=globWidth/(int)Math.pow(div-1,i+1);
            System.out.println(deltaH[i]); 
            //System.out.println(deltaV[i]); 

            if (this.deltaV[i]==0){
                this.deltaV[i]=1;
            } else if (this.deltaH[i]==0){
                this.deltaH[i]=1;
            }

        }
        for(int i=0;i<symLoc.length;i++){
            horzSymb.put(symLoc[i],i);
            vertSymb.put(symLoc[i],i);
        }
        this.tprListener=t; 
        //this.ltrListener=ltr; 
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.xCursor=p.x;
        this.yCursor=p.y; 
    }
    public void setTprListener(TprListener t){
        this.tprListener=t;
    }
     //public void addLtrListener(LetterListener t){
        //this.ltrListener=t;
    //}

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
            xCursor=horzSymb.get(keyIn)*deltaH[currDepth-1]+paddingX;
            System.out.println(horzSymb.get(keyIn));
            System.out.println("pos X="+xCursor);
            System.out.println("deltaH"+deltaH[currDepth-1]);
            xInput=false; 
            tprListener.eraseListener();
            tprListener.typeListener(keyIn,true);
       } else if(xInput==false){
            yCursor=vertSymb.get(keyIn)*deltaV[currDepth-1]+paddingY ;
            xInput=true; 
            System.out.println("pos Y="+yCursor);
            tprListener.typeListener(keyIn,false); 
       }
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
        //robot.mousePress(InputEvent.BUTTON1_MASK);
        //robot.mouseRelease(InputEvent.BUTTON1_MASK);
    } else if(keyIn==rightClick){
        robot.mouseRightClick(); 
        //robot.mousePress(InputEvent.BUTTON3_MASK);
        //robot.mouseRelease(InputEvent.BUTTON3_MASK);
    } else if(keyIn==holdRelClick){
        if(mousePress){
            robot.mouseLeftRelease(); 
            //robot.mouseRelease(InputEvent.BUTTON3_MASK);
            mousePress=false; 
        } else{
            robot.mouseLeftPress(); 
            //robot.mousePress(InputEvent.BUTTON3_MASK);
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
        }
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
