package ChristmasTree;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Branch implements XmasShape {
    int x;
    int y;
    double sx = 0.7;
    double sy = 0.5;
    Color lineColor = Color.GREEN;
    Color fillColor = Color.GREEN;

    public Branch () {}

    public Branch (int x, int y, double sx, double sy) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(sx,sy);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(41, 146, 5));
        /*int x[]={286,286,223,200,148,119,69,45,0};
        int y[]={0,131,89,108,79,95,66,80,56};*/
        int x[]={500,500,490,440, 460,400,430,350,380,300,330,250,500};
        int y[]={0,100,89,100,89,100, 85,100,79,100,79,90,0};
        g2d.fillPolygon(x,y,x.length);
    }

}
