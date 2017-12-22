package ChristmasTree;

import java.awt.*;


public class Bubble implements XmasShape {
        int x;
        int y;
        double scale;
        Color lineColor;
        Color fillColor;

        public Bubble(){
            x=0;
            y=0;
            scale =1;
            lineColor = Color.PINK;
            fillColor = Color.PINK;
        }

        public Bubble(Color line, Color fill, int x, int y, double scale) {
            lineColor = line;
            fillColor = fill;
            this.x = x;
            this.y = y;
            this.scale = scale;
        }

        public void setColors(Color line, Color fill) {
            lineColor = line;
            fillColor = fill;
        }
        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void render(Graphics2D g2d) {
            // ustaw kolor wypełnienia
            g2d.setColor(fillColor);
            g2d.fillOval(0,0,100,100);
            // ustaw kolor obramowania
            g2d.setColor(lineColor);
            g2d.drawOval(0,0,100,100);
        }

        @Override
        public void transform(Graphics2D g2d) {
            g2d.translate(x,y);
            g2d.scale(scale,scale);
        }
    }