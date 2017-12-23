package ChristmasTree;

import java.awt.*;

public class Trunk implements XmasShape {

    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public Trunk(int x, int y, double s) {
        this.x = x;
        this.y = y;
        scale = s;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(83, 77, 59));
        g2d.fillRect(0,0,50,100);;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}

