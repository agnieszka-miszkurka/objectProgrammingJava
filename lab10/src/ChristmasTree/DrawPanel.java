package ChristmasTree;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;
import java.util.concurrent.ThreadLocalRandom;

public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<>();

   /* public void paintComponent(Graphics g){
        g.setFont(new Font("Helvetica", Font.BOLD, 18));
        g.drawString("Hello World", 20, 20);

    }*/

    DrawPanel(){
        setBackground(new Color(121,173,195));
        setOpaque(true);
    }

    public void AddBranches() {
        double s=0.3;
        for (int i=-100; i<300 ; i = i+50, s+=0.1) {
            Branch branch = new Branch(-i,i+200, s,1);
            shapes.add(branch);
            Branch branch2 = new Branch(i+500,i+200, -1*s,1);
            shapes.add(branch2);
        }
    }

    private void addBubbles() {
        int numberOfBubles = 25;
        double scale = 0.2;
        for (int i=0; i<numberOfBubles; i++) {
            //color
            int r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            int g = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            int b = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            int x =100;
            Color color = new Color(r, g, b);
            int y = ThreadLocalRandom.current().nextInt(120, 550 + 1);
            if (y<150){
                x = ThreadLocalRandom.current().nextInt(220, 260 + 1);
            }else if (y<200){
                x = ThreadLocalRandom.current().nextInt(180, 290 + 1);
            }else if (y<300) {
                x = ThreadLocalRandom.current().nextInt(150, 330 + 1);
            } else if (y<400) {
                 x = ThreadLocalRandom.current().nextInt(100, 380 + 1);
            } else{
                 x = ThreadLocalRandom.current().nextInt(80, 400 + 1);
            }
            Bubble bubble = new Bubble(color,color.darker(),x,y,scale);
            shapes.add(bubble);
        }

    }

    private void addTrunk(){
        Trunk trunk = new Trunk(220,500,1);
        shapes.add(trunk);
    }

    private void addLights() {

        int x = 10, x1=10;
        for (int i=140; i<=500; i=i+100) {
            if (i<150){
                x = ThreadLocalRandom.current().nextInt(220, 260 + 1);
                x1 = ThreadLocalRandom.current().nextInt(220, 260 + 1);
            }else if (i<200){
                x = ThreadLocalRandom.current().nextInt(180, 290 + 1);
                x1 = ThreadLocalRandom.current().nextInt(180, 290 + 1);
            }else if (i<300) {
                x = ThreadLocalRandom.current().nextInt(150, 330 + 1);
                x1 = ThreadLocalRandom.current().nextInt(150, 330 + 1);
            } else if (i<400) {
                x = ThreadLocalRandom.current().nextInt(100, 380 + 1);
                x1 = ThreadLocalRandom.current().nextInt(100, 380 + 1);
            } else{
                x = ThreadLocalRandom.current().nextInt(80, 400 + 1);
                x1 = ThreadLocalRandom.current().nextInt(80, 400 + 1);
            }
            Light light = new Light(x,i,1);
            Light light2 = new Light(x1,i,1);
            shapes.add(light);
            shapes.add(light2);
        }
    }
    private void addStar() {
        Star star = new Star(250,70,1);
        shapes.add(star);
    }

    private void addWishes() {
        Wishes wishes = new Wishes(500,350,0.7,"Happy new year!");
        shapes.add(wishes);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D)g;
        addWishes();
        addTrunk();
        AddBranches();
        addBubbles();
        addLights();
        addStar();



        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }
    /*
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int x[]={286,253,223,200,148,119,69,45,0};
        int y[]={0,101,89,108,79,95,66,80,56};
        g.fillPolygon(x,y,x.length);

        Graphics2D g2d= (Graphics2D)g;
        // zachowaj macierz przekształcenia
        AffineTransform mat = g2d.getTransform();
        // przesuń początek układu
        g2d.translate(100,100);
        // zastosuj skalowanie
        g2d.scale(.2,.2);
        // narysuj linie
        for(int i=0;i<12;i++){
            g2d.drawLine(0,0,100,100);
            g2d.rotate(2*Math.PI/12);
        }
        //oddtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat);

        g2d.translate(200,200);
        // zastosuj skalowanie
        g2d.scale(.2,.2);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 96);
        g2d.setFont(font);
        for(int i=0;i<12;i++){
            g2d.drawString("Happy new year",150,0);
            g2d.rotate(2*Math.PI/12);
        }


        // zachowaj macierz przekształcenia
        AffineTransform mat2 = g2d.getTransform();
        // przesuń początek układu
        g2d.translate(200,200);
        // zastosuj skalowanie
        g2d.scale(.2,.2);
        g2d.setStroke(new BasicStroke(50, CAP_ROUND,JOIN_MITER));
        for(int i=0;i<12;i++){
            //g2d.drawString("Happy new year",150,0);
            g2d.drawLine(0,0,100,100);
            g2d.rotate(2*Math.PI/12);
        }
        //oddtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat2);
    }*/

}