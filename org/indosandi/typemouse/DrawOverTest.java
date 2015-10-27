package org.indosandi.typemouse; 
import java.awt.geom.*;
import java.awt.*; 
import javax.swing.*;        
public class DrawOverTest {
    int xDim=1440;
    int yDim=900;
    int tranp=40;
    Color backnd=new Color(0,0,0,tranp); 
    Color lineCol=new Color(0,0,0);
    Color lettCol=new Color(0,0,0);
    Dimension d = new Dimension(xDim,yDim);
    
    public DrawOverTest(){
        JFrame frame = new JFrame("glass pane test");
        frame.setPreferredSize(d);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));
        panel.add(new LabelGlassPane(frame));
        panel.setBackground(backnd); 

        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setAlwaysOnTop(false);
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);

        frame.getContentPane().add(panel);
        //frame.getContentPane().add(new LabelGlassPane(frame));
        frame.requestFocus();
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }
public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            new DrawOverTest(); 
            }
        });
        //panel.add(new JButton("my button"));
    //panel.add(new JLabel("my button"));
        //panel.add(new JLabel("my button"));
    //panel.add(new JButton("my button"));
    //Container cnt=frame.getContentPane();
    //cnt.setBackground(Color.YELLOW); 


    
    //LabelGlassPane glass = new LabelGlassPane(frame);
    //frame.setGlassPane(glass);
    //glass.setVisible(true);
}


}

class LabelGlassPane extends JComponent {
    public LabelGlassPane() {
    }
    public LabelGlassPane(JFrame frame) {
      this.frame = frame;
    }
    public JFrame frame;
    public void paint(Graphics g) {
        g.setColor(Color.red);
        //Container root = frame.getContentPane();
        g.setColor(new Color(100,100,100,100));
        g.drawString("testing",50,50);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(new Line2D.Double(0,0, 50, 50));
        //rPaint(root,g);
    }
    //private void rPaint(Container cont, Graphics g) {
        //for(int i=0; i<cont.getComponentCount(); i++) {
            //Component comp = cont.getComponent(i);
            //if(!(comp instanceof JPanel)) {
                //int x = comp.getX();
                //int y = comp.getY();
                //int w = comp.getWidth();
                //int h = comp.getHeight();
                ////g.drawRect(x+4,y+4,w-8,h-8);
                //g.drawString(comp.getClass().getName(),x+10,y+20);
            //}
            //if(comp instanceof Container) {
                //rPaint((Container)comp,g);
            //}
        //}
    //}
}
