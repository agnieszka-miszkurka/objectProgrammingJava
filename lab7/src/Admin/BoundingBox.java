package Admin;

public class BoundingBox {
    Double xmin;
    Double ymin;
    Double xmax;
    Double ymax;

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y){
        if (!this.isEmpty()) {
            if(y<ymin)
                ymin = y;
            else if(y>ymax)
                ymax = y;

            if(x>xmax)
                xmax = x;
            else if(x<xmin)
                xmin = x;
        } else {
            xmin = x;
            xmax = x;
            ymax = y;
            ymin = y;
        }

    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){
        return x>=xmin && x<=xmax && y<=ymax && y>=ymin;
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb){
        return bb.xmin>=xmin && bb.ymin>=ymin && bb.ymax<=ymax && bb.xmax<=xmax;
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){
        // intersects <=> co najmniej 1 pkt jest w srodku i co najmniej jeden poza
        // sprawdzam czy conajmnije 1 jest w srodku
        if  (contains(bb.xmax, bb.ymin) || contains(bb.xmax, bb.ymax) || contains(bb.xmin, bb.ymin) || contains(bb.xmin, bb.ymax)){
            //teraz sprawdzam czy co najmnije 1 jest poza
            if(!contains(bb.xmax, bb.ymin) || !contains(bb.xmax, bb.ymax) || !contains(bb.xmin, bb.ymin) || !contains(bb.xmin, bb.ymax)){
                return true;
            }
        }
        return false;
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        if (!bb.isEmpty()) {
            this.addPoint(bb.xmax, bb.ymax);
            this.addPoint(bb.xmin, bb.xmin);
            this.addPoint(bb.xmax, bb.ymin);
            this.addPoint(bb.xmin, bb.ymax);
        }

        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        return xmin==null || xmax == null || ymax == null || ymin == null;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() throws EmptyBoundingBoxException {
        if (!this.isEmpty()){
            double xsr = (xmax - xmin)/2;
            return xsr;
        }

        throw new EmptyBoundingBoxException("Cannot get center X of empty bounding box!");
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() throws EmptyBoundingBoxException {
        if (!this.isEmpty()){
            double ysr = (ymax - ymin)/2;
            return ysr;
        }

        throw new EmptyBoundingBoxException("Cannot get center X of empty bounding box!");
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx) throws EmptyBoundingBoxException {
            double xt = this.getCenterX();
            double yt = this.getCenterY();
            double yb = bbx.getCenterY();
            double xb = bbx.getCenterX();
            return Math.sqrt(Math.pow(xt-xb,2)+Math.pow(yt-yb,2)) * 111.196672;
    }

    @Override
    public String toString() {
        return "\nxmax: " + Double.toString(xmax) + "\nymax: " + Double.toString(ymax) + "\nxmin: " + Double.toString(xmin)
                + "\nymin: " + Double.toString(ymin);
    }

}
