package Admin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdminUnit {
    private String name;

    public int getAdminLevel() {
        return adminLevel;
    }

    private int adminLevel;

    public double getPopulation() {
        return population;
    }

    private double population;

    public double getArea() {
        return area;
    }

    private double area;

    public double getDensity() {
        return density;
    }

    private double density;

    public void setParent(AdminUnit parent) {
        this.parent = parent;
    }

    private AdminUnit parent;

    public BoundingBox getBox() {
        return box;
    }

    private BoundingBox box = new BoundingBox();

    public List<AdminUnit> getChildren() {
        return children;
    }

    private List<AdminUnit> children = new ArrayList<>();

    public List<AdminUnit> getNeighbours() {
        return neighbours;
    }

    List<AdminUnit> neighbours  = new ArrayList<>();

    AdminUnit(String name, double area, int admin_level, double population, double density) {
        this.name = name;
        this.area = area;
        this.adminLevel = admin_level;
        this.population = population;
        this.density = density;
    }

    @Override
    public String toString() {
        return name + " " + Double.toString(area) + " "
                + Double.toString(adminLevel) + " "
                + Double.toString(population) + " "
                + Double.toString(density);
    }

    String getName() {
        return name;
    }

    public AdminUnit getParent() {
        return parent;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    void fixMissingValues() {
        if (this.getDensity() == -1) {
            if (this.getParent() != null) {
                if(this.parent.getDensity()==-1) {
                    this.parent.fixMissingValues();
                }
                this.setDensity(this.parent.getDensity());
            } else {
                this.density = 0;
            }
        }

        if (this.getPopulation() == -1) {
            this.population = this.getArea()*this.getDensity();
        }
    }


}
