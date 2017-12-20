package Admin;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Admin.AdminUnit;
import csvReader.CSVReader;
import csvReader.EmptyFieldException;

public class AdminUnitList {
    public List<AdminUnit> getUnits() {
        return units;
    }

    private List<AdminUnit> units = new ArrayList<>();
    private Map<AdminUnit,Long> parentIdMap = new HashMap<>();
    private Map<Long, AdminUnit> selfIdMap = new HashMap<>();

    private Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();

    public Map<Long, List<AdminUnit>> getParentid2child() {
        return parentid2child;
    }

    void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename);
        while (reader.next()) {
            String name;
            double area;
            int admin_level;
            double population;
            double density;
            long parentID;
            long ID;

            area = reader.getDouble("area");
            name = reader.get("name");
            admin_level = reader.getInt("admin_level");
            population = reader.getDouble("population");
            density = reader.getDouble("density");
            parentID = reader.getLong("parent");
            ID = reader.getLong("id");

            double x1 = reader.getDouble("x1");
            double y1 = reader.getDouble("y1");
            double x2 = reader.getDouble("x2");
            double y2 =  reader.getDouble("y2");
            double x3 = reader.getDouble("x3");
            double y3 = reader.getDouble("y3");
            double x4 = reader.getDouble("x4");
            double y4 = reader.getDouble("y4");

            AdminUnit node = new AdminUnit(name, area, admin_level, population, density);

            node.getBox().addPoint(x1,y1);
            node.getBox().addPoint(x2,y2);
            node.getBox().addPoint(x3,y3);
            node.getBox().addPoint(x4,y4);

            parentIdMap.put(parentID != -1 ? node : null, parentID);
            selfIdMap.put(ID, node);
            units.add(node);
        }

        for(AdminUnit node : units){
            AdminUnit parent = selfIdMap.get(parentIdMap.get(node));
            node.setParent(parent);

            if(parent != null){
                parent.getChildren().add(node);
            }

        }
        for(AdminUnit node : units) {
            node.fixMissingValues();
        }

    }

    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     * @param out
     */
    void list(PrintStream out){
        for (AdminUnit unit : units) {
            out.println(unit.toString());
        }
    }
    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     * @param out - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out,int offset, int limit ){
        int index = 0;
        for (AdminUnit unit : units) {
            if (index>=offset && limit>0){
                limit--;
                out.println(unit.toString());
            }
            index++;
        }
    }

    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     * @param pattern - wzorzec dla nazwy
     * @param regex - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret

        for (AdminUnit unit : units) {
            String name = unit.getName();
            if(regex){
                if(name.matches(pattern)){
                    ret.units.add(unit);
                }
            } else  {
                if(name.contains(pattern)){
                    ret.units.add(unit);
                }
            }
        }
        return ret;
    }

    /**
     * Zwraca listę jednostek sąsiadujących z jendostką unit na tym samym poziomie hierarchii admin_level.
     * Czyli sąsiadami wojweództw są województwa, powiatów - powiaty, gmin - gminy, miejscowości - inne miejscowości
     * @param unit - jednostka, której sąsiedzi mają być wyznaczeni
     * @param maxdistance - parametr stosowany wyłącznie dla miejscowości, maksymalny promień odległości od środka unit,
     *                    w którym mają sie znaleźć punkty środkowe BoundingBox sąsiadów
     * @return lista wypełniona sąsiadami
     */
    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) throws EmptyBoundingBoxException {
        AdminUnitList neighborsList = new AdminUnitList();
        for(AdminUnit adminUnit : this.units){
            if (adminUnit == unit)
                continue;
            if(adminUnit.getAdminLevel() == unit.getAdminLevel()){
                if(unit.getAdminLevel() >= 8 ) {
                    if(unit.getBox().distanceTo(adminUnit.getBox()) < maxdistance)
                        neighborsList.units.add(adminUnit);
                } else {
                    if (unit.getBox().intersects(adminUnit.getBox())){
                        neighborsList.units.add(adminUnit);
                    }
                }
            }
        }
        return neighborsList;
    }

    void rTreeSearch(double maxdistance, AdminUnit adminUnit){
        if (adminUnit.getParent() == null){
            return;
        }
        rTreeSearch(maxdistance, adminUnit.getParent());

        for (AdminUnit sibling : adminUnit.getParent().getChildren()){
            if (adminUnit.getAdminLevel()>=8) {
                if (adminUnit.getBox().intersectsWithMaxDistance(sibling.getBox(),maxdistance)){
                    adminUnit.neighbours.add(sibling);
                }
            } else if (adminUnit.getAdminLevel() < 8 && adminUnit.getBox().intersects(sibling.getBox())){
                adminUnit.neighbours.add(sibling);
            }
        }

        for (AdminUnit parentNeighbour : adminUnit.getParent().neighbours){
            for (AdminUnit sibling : parentNeighbour.getChildren()){
                if (adminUnit.getAdminLevel()>=8 && adminUnit.getBox().intersectsWithMaxDistance(sibling.getBox(),maxdistance)){
                    adminUnit.neighbours.add(sibling);
                } else if (adminUnit.getAdminLevel() < 8 && adminUnit.getBox().intersects(sibling.getBox())){
                    adminUnit.neighbours.add(sibling);
                }
            }
        }
    }

    //lab9

    AdminUnitList sortInplaceByName(){

        class AdminComparator implements Comparator<AdminUnit> {

            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }

        units.sort(new AdminComparator());
        return this;
    }

    AdminUnitList sortInplaceByArea(){

        units.sort(new Comparator<AdminUnit>(){
            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return Double.compare(o1.getArea(),o2.getArea());
            }
        });
        return this;
    }

    AdminUnitList sortInplaceByPopulation(){
        units.sort(((o1, o2) -> Double.compare(o1.getPopulation(), o2.getPopulation())));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        // Tworzy wyjściową listę
        // Kopiuje wszystkie jednostki
        // woła sortInPlace
        AdminUnitList newList = new AdminUnitList();
        newList.units = new ArrayList<AdminUnit>(this.units);
        newList.units.sort(cmp);
        return newList;
    }

    /**
     *
     * @param pred referencja do interfejsu Predicate
     * @return nową listę, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */
    AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList newList = new AdminUnitList();
        newList.units = new ArrayList<AdminUnit>(this.units);
        newList.units = units.stream().filter(pred).collect(Collectors.toList());
        return newList;
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred
     * @param pred - predykat
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList newList = new AdminUnitList();
        newList.units = new ArrayList<AdminUnit>(this.units);
        newList.units = units.stream().filter(pred).limit(limit).collect(Collectors.toList());
        return newList;
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred począwszy od offset
     * Offest jest obliczany po przefiltrowaniu
     * @param pred - predykat
     * @param - od którego elementu
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList newList = new AdminUnitList();
        newList.units = new ArrayList<AdminUnit>(this.units);
        newList.units = units.stream().filter(pred).skip(offset).limit(limit).collect(Collectors.toList());
        return newList;
    }


}
