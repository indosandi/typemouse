//import javax.swing.JComponent;        
package org.indosandi.typemouse; 
import static co.Constant.*; 
import java.awt.geom.*;
import java.awt.*; 
import javax.swing.*;        

class ComponentOver extends JComponent {
    public ComponentOver() {
    }
    public ComponentOver(JFrame frame) {
      this.frame = frame;
    }
    public JFrame frame;

    public void paint(Graphics g) {
        //g.setColor(Color.red);
        //Container root = frame.getContentPane();
        g.setColor(lettCol);
        drawLet(g); 
        g.setColor(lineCol);
        //Graphics2D g2 = (Graphics2D) g;
        //drawGrid(g2); 
        //g.drawString("testing",50,50);
        //Graphics2D g2 = (Graphics2D) g;
        //g2.draw(new Line2D.Double(0,0, 50, 50));
        //rPaint(root,g);
    }
    private void drawGrid(Graphics2D g){
        for(int i=0; i< div; i++){
           int xinit=i*(globWidth/div);
           int yinit=i*(globHeight/div); 
           g.draw(new Line2D.Float(xinit,0,xinit,globHeight));
           g.draw(new Line2D.Float(0,yinit,globWidth,yinit)); 
        }
    }
    private void drawLet(Graphics g){
        int deltaV=globHeight/(div-1);
        int deltaH=globWidth/(div-1);
        for(int i=0; i< div; i++){
            for(int j=0; j<div; j++){
               //System.out.println(i); 
               //System.out.println(j); 
               int xinit=i*deltaH;
               int yinit=j*deltaV;  
               String str=Character.toString(symLoc[i])+Character.toString(symLoc[j]); 
               //System.out.println(str); 
               g.setFont(new Font(fontType, Font.PLAIN, fontSize)); 
               g.drawString(str,xinit,yinit);
           }
        }

    }
}
