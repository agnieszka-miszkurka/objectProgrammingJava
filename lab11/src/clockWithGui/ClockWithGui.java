package clockWithGui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);

        g2d.setColor(new Color(196,222,222));
        g2d.setStroke(new BasicStroke(10));
        g2d.fillOval(-140,-140,275,275);
        g2d.setColor(new Color(46,79,88));
        g2d.drawOval(-140,-140,275,275);
        g2d.setColor(Color.BLACK);


        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(Integer.toString(i),(int)trg.getX()-6,(int)trg.getY());
        }
        for(int i=1;i<61;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/60*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            if(i%5==0){
                g2d.setStroke(new BasicStroke(4));
            } else {
                g2d.setStroke(new BasicStroke(1));
            }
            g2d.drawLine((int)(trg.getX()*0.9),(int)(trg.getY()*0.9)-2,(int)(trg.getX()*0.8),(int)(trg.getY()*0.8)-2);
            g2d.setStroke(new BasicStroke(1));
        }

        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(time.getSecond()%60*2*Math.PI/60);
        g2d.drawLine(0,0,0,-90);
        g2d.setTransform(saveAT);

        g2d.rotate(time.getHour()%12*2*Math.PI/12);
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(0,0,0,-50);
        g2d.setTransform(saveAT);

        g2d.setColor(Color.BLACK);
        g2d.rotate(time.getMinute()%60*2*Math.PI/60);
        g2d.drawLine(0,0,0,-90);
        g2d.setTransform(saveAT);


    }

    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }

    public ClockWithGui(){
        new ClockThread().start();
        setOpaque(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

}

