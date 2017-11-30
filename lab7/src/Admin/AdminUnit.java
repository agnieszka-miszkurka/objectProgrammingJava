package Admin;

public class AdminUnit {
    private String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();

    public String getName() {
        return name;
    }

    public AdminUnit(String name, int adminLevel, double population, double area, double density){
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
    }

    @Override
    public String toString(){
        return name + " area:" + Double.toString(area) + " population:" + Double.toString(population);
    }
}
