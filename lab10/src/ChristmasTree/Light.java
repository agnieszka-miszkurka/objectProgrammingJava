package ChristmasTree;

import java.awt.*;

public class Light implements XmasShape {
    int x;
    int y;
    double scale;
    Color candleColor;
    Color fireColor;

    public Light(int x, int y, double s) {
        this.x = x;
        this.y = y;
        scale = s;
        candleColor = new Color(254, 246, 210);
        fireColor = new Color(237, 215, 15);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(candleColor);
        g2d.fillRect(0,0,7,18);
        g2d.setColor(fireColor);
        //g2d.fillOval(2,4,12,3);
        g2d.fillOval(0,-10,6,10);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
