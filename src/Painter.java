import javax.swing.*;
import java.awt.*;

public class Painter extends JComponent {
    private Complex c;
    private int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private Complex z;
    private boolean wasOut;
    public double leftBound = -2;
    public double rightBound = 2;
    public double lowerBound = -2;
    public double upperBound = 2;
    public double x = 0;
    public double y = 0;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(double i = 0; i < WIDTH+1; i++) {
            for(double j = 0; j < HEIGHT+1; j++) {
                wasOut = false;
                z = new Complex(0,0);
                c = new Complex((rightBound-leftBound)*(i-(WIDTH/2))/WIDTH+x,(upperBound-lowerBound)*(j-(HEIGHT/2))/HEIGHT+y);
                int countMax=30;
                for(double count = 0; count < countMax; count++) {
                    if(Math.abs(z.re)>2||Math.abs(z.im)>2) {
                        if(!wasOut) {
                            int color = (int)((count/countMax)*255);
                            //int color = 0;
                            g.setColor(new Color(color,color,255));
                            g.drawLine((int)(i*1),(int)((j)-HEIGHT/4.8),(int)(i*1),(int)(j-HEIGHT/(4.8)));
                        }
                        wasOut=true;
                    }
                    Complex oldZ = z;
                    //for(int d = 0; d < 1; d++) {
                        z=z.times(oldZ);
                    //}
                    z = z.plus(c);
                }
                if(!wasOut) {
                    g.setColor(Color.black);
                    g.drawLine((int)(i*1),(int)((j)-HEIGHT/4.8),(int)(i*1),(int)(j-HEIGHT/(4.8)));
                }
            }
        }
        //repaint();
    }
    public void draw() {
        repaint();
    }
}
