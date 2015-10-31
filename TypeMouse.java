import static co.Constant.*;
import java.awt.image.BufferedImage; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.awt.AWTException; 
import java.awt.Rectangle;  
import java.awt.GridLayout; 
public class TypeMouse extends JFrame
        implements KeyListener,
        ActionListener, TprListener
{
    JTextArea displayArea;
    JTextField typingArea;
    CompLett sglLetter; 
    Timer timer; 
    int xpos=10;
    int ypos=10; 
    int counter=0; 
    ComponentOver cmpOver; 
     
    TypeParseRobot tpr=new TypeParseRobot(this);          
    public TypeMouse(){
        createAndShowGUI(); 
               timer = new Timer(550, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xpos=100;
                ypos=100; 
                counter++;
                //sglLetter.setStr("coba coba");     
                sglLetter.setStr(Integer.toString(counter));     
                //sglLetter.setPos(xpos,ypos); 
                Rectangle r=new Rectangle(0,0,globWidth,globHeight); 
                //System.out.println(xpos); 
                //System.out.println(ypos); 
                //cmpOver.revalidate(); 
                //cmpOver.repaint(r); 
                sglLetter.revalidate(); 
                sglLetter.repaint(r); 
        }
      });
      timer.setCoalesce(true);
      timer.setInitialDelay(0);
      timer.start(); 

    }
    private void addAllListener(){
       tpr.setTprListener(this); 
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        //tpr.setTprListener(this);
        //TypeMouse frame = new TypeMouse();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addAllListener();  
        Dimension d=new Dimension(globWidth,globHeight); 
        this.setPreferredSize(d); 
        this.setUndecorated(true);
        this.setBackground(backnd);
        this.setAlwaysOnTop(false);
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);

        this.addComponentsToPane();
        
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
     
    private void addComponentsToPane() {
         
        typingArea = new JTextField(3);
        typingArea.addKeyListener(this);
        ComponentOver cmpOver=new ComponentOver(this);  
                //typingArea.setFocusTraversalKeysEnabled(false); 
        sglLetter=new CompLett(this); 
        getContentPane().add(typingArea);
        //getContentPane().add(cmpOver);
        getContentPane().add(sglLetter);  
        //getContentPane().add(typingArea, BorderLayout.PAGE_START);
    
    }
     
         
     
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        tpr.recInput(c); 
        }
     
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");
    }
     
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }
     
    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        //Clear the text components.
        displayArea.setText("");
        typingArea.setText("");
        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
    }
    public void typeListener(int i, boolean b){
        sglLetter.setStr(Integer.toString(i)); 
        //System.out.println(i); 
        //System.out.println(b); 
        if (b==true){
            sglLetter.setISel(i);  
            sglLetter.setBFirst(b); 
        } 
        else{
            sglLetter.setJSel(i); 
            sglLetter.setBFirst(b); 
        }
    }
    public void eraseListener(){
        typingArea.setText("");
    }

    public void imageListener(BufferedImage b,int i){
        sglLetter.setBuffer(b);         
        sglLetter.setIDepth(i); 
        //System.out.println("depth="+i); 
    }
   public void posListener(int[] x, int[] y){
        sglLetter.setPrev(x,y); 
   } 
}

