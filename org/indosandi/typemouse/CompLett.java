package org.indosandi.typemouse; 
import static co.Constant.*; 
import java.awt.geom.*;
import java.awt.*; 
import javax.swing.*;        
class CompLett extends JComponent {
    int x=0;
    int y=0;
    String str="testiiing"; 
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
        //g.setColor(Color.red);
        //Container root = frame.getContentPane();
       //drawLet(g); 
       g.setFont(new Font(fontType, Font.PLAIN, fontSize)); 
        System.out.println(str); 
       //g.dispose(); 
       this.x=x+50; 
       g.drawString(str,x,x);
    //Graphics2D g2 = (Graphics2D) g;
        //drawGrid(g2); 
        //g.drawString("testing",50,50);
        //Graphics2D g2 = (Graphics2D) g;
        //g2.draw(new Line2D.Double(0,0, 50, 50));
        //rPaint(root,g);
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
