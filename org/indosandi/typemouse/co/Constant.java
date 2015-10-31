package org.indosandi.typemouse.co; 
import java.awt.Color;
import java.awt.Font; 
public final class Constant{
    public static final int trnsp=40;
    public static final Color backnd=new Color(0,0,0,trnsp); 
    public static final Color lineCol=new Color(0,0,0);
    public static final Color lettCol=new Color(0,0,0);
    public static final Color lettColSel=Color.RED;

    public static final char[] symLoc={'a','s','d','f','w','e','x','c','v','q'}; 
    public static final char leftClick='t';
    public static final char holdRelClick='r';
    public static final char rightClick='g';
    public static final char wheelClick='u';
    public static final char[] dirSymb={'h','j','k','l'}; 
    public static final char depthBack='i'; 
    public static final char depthForward='o'; 
    public static final char changeFocus='q'; 
    public static final int globHeight=900;
    public static final int globWidth=1440;
    public static final int[] div={10,10,10};
    public static final int maxDepth=3;
    public static final int minDepth=1;
    public static int[] paddingX={0,5,0};
    public static int[] paddingY={0,18,0};
    public static final String fontType="TimesRoman";
    public static final int[] fontSize={35,12,5}; 
    public static final float scaleTrans=3.0f; 
    public static final int timeTic=500; 
    public static final Font letFont=new Font(fontType, Font.PLAIN,fontSize[1]); 
    public static final float trsScr=1.0f; 
    public static final float trsTxt=.4f; 
    
    public static  int[] deltaH=new int[maxDepth]; 
    public static  int[] deltaV=new int[maxDepth]; 
}
