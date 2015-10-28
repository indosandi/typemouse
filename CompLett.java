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
    static int xprev=0;
    static int xpos=0;
    static int ypos=0;
    static int yprev=0;
    public String str="s";  

    public static int iSel=-1;
    public static int jSel=-1;
    public static boolean bFirst=false; 
    protected static final int ZOOM_AREA = 40;
    private float zoomLevel = 2f;
    private BufferedImage buffer;

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
        g.setFont(new Font(fontType, Font.PLAIN, fontSize)); 
        //System.out.println(str); 
        drawLet(g); 
        //drawZoom(g,2f); 
        //makeScreenshot(g); 
    }
    public void setPos(int x,int y){
        this.xprev=xpos;
        this.yprev=ypos;
        this.xpos=x;
        this.ypos=y; 
    }
    private void drawZoom(Graphics g,float zoomLevel){
                //buffer = bot.createScreenCapture(new Rectangle(50, 50, ZOOM_AREA, ZOOM_AREA));
       //Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setTransform(AffineTransform.getScaleInstance(zoomLevel, zoomLevel));
        //AffineTransform at = g2d.getTransform();
        //g2d.drawImage(buffer, 0, 0, this);
    }
    private void drawLet(Graphics g){
        int deltaV=globHeight/(div-1);
        int deltaH=globWidth/(div-1);
        g.setFont(letFont); 
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
               int xinit=i*deltaH;
               int yinit=j*deltaV;  
               String str=Character.toString(symLoc[i])+Character.toString(symLoc[j]); 
               g.drawString(str,xinit,yinit);
           }
        }
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

}
