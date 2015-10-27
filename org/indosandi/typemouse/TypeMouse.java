package org.indosandi.typemouse;
import static co.Constant.*;
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
    //CompLett sglLetter; 
    //static final String newline = System.getProperty("line.separator");
     
    TypeParseRobot tpr=new TypeParseRobot(this);          
    //public static void main(String[] args) throws AWTException {
       //try {
            ////UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            ////UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        //} catch (UnsupportedLookAndFeelException ex) {
            //ex.printStackTrace();
        //} catch (IllegalAccessException ex) {
            //ex.printStackTrace();
        //} catch (InstantiationException ex) {
            //ex.printStackTrace();
        //} catch (ClassNotFoundException ex) {
            //ex.printStackTrace();
        //}
        //[> Turn off metal's use of bold fonts <]
        //UIManager.put("swing.boldMetal", Boolean.FALSE);
        ////Schedule a job for event dispatch thread:
        ////creating and showing this application's GUI.
        //javax.swing.SwingUtilities.invokeLater(new Runnable() {
            //public void run() {
                //createAndShowGUI(); 
                ////new TypeMouse(); 
                ////createAndShowGUI();
            //}
        //});

    //}
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public TypeMouse(){
        createAndShowGUI(); 
    }
    private void addAllListener(){
        //tpr.addLtrListener(this); 
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        //tpr.setTprListener(this);
        //TypeMouse frame = new TypeMouse();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        Dimension d=new Dimension(globWidth,globHeight); 
        this.setPreferredSize(d); 
        this.setUndecorated(true);
        this.setBackground(backnd);
        this.setAlwaysOnTop(false);
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);

        //frame.setPreferredSize(d); 
        //frame.setUndecorated(true);
        //frame.setBackground(backnd);
        //frame.setAlwaysOnTop(false);
        //frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);
               //JPanel panel = new JPanel();
        //panel.setLayout(new GridLayout(1,1));
        //panel.add(new ComponentOver(frame));
        //panel.setBackground(backnd); 

        //frame.getContentPane().add(panel);

        //addKeyListener(this)
        //Set up the content pane.
        this.addComponentsToPane();
        //frame.addComponentsToPane();
        //frame.setFocusable(true);

        this.setResizable(false);
        //frame.setResizable(false);
        //Display the window.
        //frame.pack();
        //frame.setVisible(true);
        this.pack();
        this.setVisible(true);
    }
     
    private void addComponentsToPane() {
         
        typingArea = new JTextField(3);
        typingArea.addKeyListener(this);
        ComponentOver cmpOver=new ComponentOver(this);  
        //ComponentOver.addKeyListener(this);
        //getContentPane().add(cmpOver);
        //typingArea.setFocusTraversalKeysEnabled(false); 
        //sglLetter=new CompLett(this); 
        getContentPane().add(typingArea);
        //getContentPane().add(sglLetter);  
        //getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(cmpOver);
    
    }
     
    //public KeyEventDemo(String name) {
        //super(name);
    //}
     
     
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        tpr.recInput(c); 
        //String str="x="+tpr.getXCursor()+" "+"y="+tpr.getYCursor(); 
        //displayArea.setText(str); 
        //displayArea.replaceRange("",0,20); 
        //displayArea.insert(str,0);
        //System.out.println(c); 
        //displayInfo(e, "KEY TYPED: ");
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
     
    public void typeListener(char let, boolean x){
        //String str="x="+tpr.getXCursor()+" "+"y="+tpr.getYCursor(); 
        //displayArea.setText(str); 
        //System.out.println("typeListener"); 
        //System.out.println("disini"); 
        //sglLetter.setStr("coba coba");     
        //Rectangle r=new Rectangle(100,100, 50,50); 
        //sglLetter.revalidate(); 
        //sglLetter.repaint(r); 
    }
    public void eraseListener(){
        typingArea.setText("");
    }
    public void letListen(char c, boolean b){
    }
}

