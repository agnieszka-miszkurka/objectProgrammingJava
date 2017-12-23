package ChristmasTree;

import java.awt.*;

public class Star implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public Star(int x, int y, double s) {
        this.x = x;
        this.y = y;
        scale = s;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(237, 221, 44));
        int x[]={0,30,-30,0};
        int y[]={-4,35,35,-4};
        g2d.fillPolygon(x,y,x.length);
        int x1[]={0,30,-30,0};
        int y1[]={45,10,10,45};
        g2d.fillPolygon(x1,y1,x.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
