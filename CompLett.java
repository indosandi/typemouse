import java.awt.geom.AffineTransform;
import static co.Constant.*; 
import java.io.File; 
import java.awt.image.BufferedImage; 
import java.awt.geom.*;
import java.awt.*; 
import javax.swing.*;        
import java.io.IOException; 
import javax.imageio.ImageIO; 
import java.awt.AWTException; 
import java.awt.Robot;
class CompLett extends JComponent {
    //static int xprev=0;
    //static int xpos=0;
    //static int ypos=0;
    //static int yprev=0;
    public String str="s";  
    public int iDepth=1; 

    public static int iSel=-1;
    public static int jSel=-1;
    int[] xPrev={0,0,0};
    int[] yPrev={0,0,0};
    int jSelPrev=-1;
    public static boolean bFirst=false; 
    protected static final int ZOOM_AREA = 40;
    private float zoomLevel = 2f;
    public BufferedImage buffer;

    public CompLett() {
    }
    public CompLett(JFrame frame) {
      this.frame = frame;
    }
    public JFrame frame;
    public void setStr(String s){
        this.str=s; 
    }

    public void paint(Graphics g) {
        g.setFont(new Font(fontType, Font.PLAIN, fontSize[iDepth-1])); 
        //g.setFont(new Font(fontType, Font.PLAIN, fontSize[0])); 
        //System.out.println(iDepth); 
        //int dV=globHeight/(int)Math.pow(div-1,iDepth); 
        //int dH=globWidth/(int)Math.pow(div-1,iDepth); 
        if (iDepth==1){
            //int dV=globHeight/(div-1);
            //int dH=globWidth/(div-1);
            //drawLet(g,dV,dH,0,0); 
            drawLet(g,deltaV[iDepth-1],deltaH[iDepth-1],0,0); 
        }
        else{
            //int dV=globHeight/(div-1);
            //int dH=globWidth/(div-1);
            //drawLet(g,dV,dH,iSel*dH,iSel*dV); 
            //System.out.println(iSel); 
            //System.out.println(jSel); 
            //System.out.println(deltaH[iDepth-2]); 
            //System.out.println(deltaV[iDepth-2]); 
            //System.out.println((iSel+1)*deltaH[iDepth-2]); 
            //System.out.println((jSel+1)*deltaV[iDepth-2]); 
            int xTotal=0; 
            int yTotal=0; 
            for(int i=0;i<iDepth-1;i++){
                xTotal+=xPrev[i]; 
                yTotal+=yPrev[i]; 
            }
            System.out.println(xTotal); 
            drawLet(g,deltaV[iDepth-1],deltaH[iDepth-1],xTotal,yTotal); 
            //drawLet(g,deltaV[iDepth-1],deltaH[iDepth-1],xPrev[iDepth-2],yPrev[iDepth-2]); 
            //drawLet(g,deltaV[iDepth-1],deltaH[iDepth-1],(iSel)*deltaH[iDepth-2],(jSel)*deltaV[iDepth-2]); 

            Graphics2D g2 = (Graphics2D) g;
            g2.setTransform(AffineTransform.getScaleInstance(3.0f,3.0f)); 
            g2.drawImage(buffer,null,0,0); 
            //g2.setTransform(AffineTransform.getScaleInstance(1,1)); 
            //g2.drawString("testing",0,globHeight/2); 
        }
        xPrev[iDepth-1]=iSel*deltaH[iDepth-1]; 
        yPrev[iDepth-1]=jSel*deltaV[iDepth-1]; 
        //RobotWrapper rw=new RobotWrapper();
        //BufferedImage bf=rw.captureScr(50,50,50,50); 
                //Graphics2D g2d = bf.createGraphics();
        //g2d.setColor(Color.red);
        //g2d.fill(new Ellipse2D.Float(0, 0, 200, 100));
        //g=g2d; 
        //drawZoom(g,2f); 
        //makeScreenshot(g); 
    }
    public void setPrev(int[] x,int[] y){
        this.xPrev=x;
        this.yPrev=y;
        //this.xpos=x;
        //this.ypos=y; 
    }
    private void drawZoom(Graphics g,float zoomLevel){
                //buffer = bot.createScreenCapture(new Rectangle(50, 50, ZOOM_AREA, ZOOM_AREA));
       //Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setTransform(AffineTransform.getScaleInstance(zoomLevel, zoomLevel));
        //AffineTransform at = g2d.getTransform();
        //g2d.drawImage(buffer, 0, 0, this);
    }
    private void drawLet(Graphics g, int deltaV, int deltaH,int padX,int padY){
        //int deltaV=globHeight/(div-1);
        //int deltaH=globWidth/(div-1);
        //g.setFont(letFont); 
        for(int i=0; i< div; i++){
            for(int j=0; j<div; j++){
               if (i==iSel & j==jSel & bFirst==false){
                    g.setColor(lettColSel); 
                    //System.out.println(" double line red"); 
                }
                else if(i==iSel & bFirst){
                    g.setColor(lettColSel); 
                    //System.out.println("line red"); 
                }
                else{
                    g.setColor(lettCol);
                    //System.out.println("black"); 
                }
               int xinit=i*deltaH+padX;
               int yinit=j*deltaV+padY;  
               String str=Character.toString(symLoc[i])+Character.toString(symLoc[j]); 
               //String str=Integer.toString(xinit)+" "+Integer.toString(yinit); 
               //if(iDepth==1){ 
               g.drawString(str,xinit,yinit);
               //}
           }
        }
               //g.drawString("test",padX,padY);
    }
    public void makeScreenshot(Graphics g) {
        //Rectangle rec = frame.getBounds();
        //BufferedImage bufferedImage = new BufferedImage(rec.width, rec.height,BufferedImage.TYPE_INT_ARGB);
        //try {
            //File temp = File.createTempFile("screenshot", ".png");
            //ImageIO.write(bufferedImage, "png", temp);
            //temp.deleteOnExit();
            //System.out.println("image out"); 
        //} catch (IOException ioe) {
            //System.out.println("catch exception"); 
            //} 
        }
    public void setISel(int i){
        this.iSel=i; 
    }
    public void setJSel(int j){
        this.jSel=j; 
    }
    public int getISel(){
        return this.iSel; 
    }
    public int getJSel(){
        return this.jSel; 
    }
    public boolean getBFirst(){
        return this.bFirst; 
    }
    public void setBFirst(boolean b){
       this.bFirst=b; 
    }
    public void setBuffer(BufferedImage b){
        this.buffer=b; 
    }
    public void setIDepth(int i){
        this.iDepth=i; 
    }

}
