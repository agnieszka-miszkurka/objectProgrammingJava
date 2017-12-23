package ChristmasTree;

import java.awt.*;

public class Wishes implements XmasShape{
    int x;
    int y;
    double s;
    String text;
    Color textColor = new Color(27, 42, 73);

    public Wishes () {}

    public Wishes (int x, int y, double s, String text) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.text = text;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(s,s);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 96);
        g2d.setFont(font);
        g2d.setColor(textColor);
        for(int i=0;i<12;i++){
            g2d.drawString(text,150,0);
            g2d.rotate(2*Math.PI/12);
        }
        Font font2 = new Font("Serif", Font.PLAIN, 70);
        g2d.setFont(font2);
        g2d.setColor(new Color(113, 25, 31));
        g2d.drawString("Merry",-80,-30);
        g2d.drawString(
                "Christmas",-140,50);
    }
}
