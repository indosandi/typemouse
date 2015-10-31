package org.indosandi.typemouse; 
import java.awt.image.BufferedImage; 
interface TprListener{
    public void typeListener(int i, boolean x); 
    public void imageListener(BufferedImage b, int i); 
    public void eraseListener(); 
    public void posListener(int[] x,int[] y); 
}
