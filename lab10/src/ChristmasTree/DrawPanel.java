package ChristmasTree;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

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
            Branch branch = new Branch((int) (20 + s*-242),i, s,1);
            shapes.add(branch);
            Branch branch2 = new Branch((int) (20 + s*332),i, -1*s,1);
            shapes.add(branch2);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D)g;
        AddBranches();
        Bubble bubble1 = new Bubble();
        bubble1.setColors(Color.BLUE, Color.PINK);
        //bubble1.render(g2d);

        Bubble bubble2 = new Bubble();
        bubble2.setColors(Color.MAGENTA, Color.MAGENTA);
        bubble2.setPosition(150,150);
        bubble2.scale = 1;
        bubble2.transform(g2d);
        //bubble2.render(g2d);
        shapes.add(bubble1);
        shapes.add(bubble2);


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