import java.awt.event.InputEvent;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.AWTException; 
import java.awt.Robot;
import java.awt.image.BufferedImage;
public class RobotWrapper {
    private final Robot robot;
    private int delLeftClick=50; 
    public RobotWrapper() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }
    public void mouseLeftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(delLeftClick); //set the delay
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public void mouseLeftPress() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }
    public void mouseLeftRelease() {
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public void mouseRightClick() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }
    public void mouseWheel( int y) {
        robot.mouseWheel(y); 
    }
    public void switchFocus(){
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(delLeftClick); //set the delay
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);
    }
    public BufferedImage captureScr(int x,int y,int width, int height){
        Rectangle r=new Rectangle(x,y,width,height); 
        BufferedImage buffer=robot.createScreenCapture(r); 
        return buffer; 
    }
}
