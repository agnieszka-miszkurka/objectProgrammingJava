package Admin;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Admin.AdminUnit;
import csvReader.CSVReader;
import csvReader.EmptyFieldException;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename,",",true);
        while(reader.next()){
            int adminLevel = 0;
            String name = "";
            double population=0.0;
            double area=0.0;
            double density=0.0;
            adminLevel = reader.getInt("admin_level");
            population = reader.getDouble("population");
            area = reader.getDouble("area");
            density = reader.getDouble("density");
            name = reader.get("name");

            AdminUnit adminUnit =  new AdminUnit(name, adminLevel, population, area, density);
            units.add(adminUnit);
            //System.out.printf(Locale.US,"%d %s %f\n",id, name, fare);
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

        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        AdminUnitList adminUnitList = new AdminUnitList();
        adminUnitList.read("admin-units.csv");
        for (AdminUnit u : adminUnitList.units) {
            System.out.println(u.toString());
        }
    }
}
