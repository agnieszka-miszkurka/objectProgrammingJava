import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BouncingBallsPanel extends JPanel {

    static class Ball{
        int x;
        int y;
        double vx;
        double vy;
        Color color;
    }

    AnimationThread animationThread;

    static List<Ball> balls = new ArrayList<>();

    static public class AnimationThread extends Thread{
        boolean suspend = false;
        boolean stop = false;

        synchronized void wakeup(){
            suspend=false;
            notify();
        }
        void safeSuspend(){
            suspend=true;
        }

        void detectColision(Ball b) {
            if(b.x<0)

        }

        public void run(){
            for(;;){
                //przesuń kulki
                for (Ball b : BouncingBallsPanel.balls ) {
                    b.x += b.vx;
                    b.y += b.vy;
                    //wykonaj odbicia od ściany
                    if (b.x==700 || b.y==700 || b.x==0 )


                }




                //wywołaj repaint
                //uśpij

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized(this){
                    try{
                        if(suspend){
                            System.out.println("suspending");
                            wait();
                        }
                    }
                    catch(InterruptedException e){}
                }
            }
        }
    }

    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
    }

    void onStart(){
        System.out.println("Start or resume animation thread");
        if (!animationThread.stop) {
            animationThread.start();
        } else  {
            animationThread.wakeup();
        }
    }

    void onStop(){
        System.out.println("Suspend animation thread");
    }

    void onPlus(){
        System.out.println("Add a ball");
    }

    void onMinus(){
        System.out.println("Remove a ball");
    }
}