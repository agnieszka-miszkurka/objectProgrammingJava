package Admin;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class Main {


    static void rTreeSearch(AdminUnit root, int maxdistance){
        //jesli ma dzieci
        if (!root.getChildren().isEmpty()) {
            for (int i=0; i<root.getChildren().size(); i++) {
                AdminUnit child1 = root.getChildren().get(i);
                for (int j=i+1; j<root.getChildren().size(); j++) {
                    AdminUnit child2 = root.getChildren().get(j);
                    if(child1.getAdminLevel() >= 8 ) {
                        if (child1.getBox().intersectsWithMaxDistance(child2.getBox(), maxdistance)){
                            child1.neighbours.add(child2);
                            child2.neighbours.add(child1);
                        }
                    } else if (child1.getBox().intersects(child2.getBox())){
                        child1.neighbours.add(child2);
                        child2.neighbours.add(child1);
                    }
                }
                //wszysy sasiedzi i sa juz wpisani
                for (AdminUnit grandchild : child1.getChildren()){
                    rTreeSearch(grandchild, maxdistance);
                }
            }
        }
    }

    public static void main(String[] args) {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminUnitList b = new AdminUnitList();
        try {
            b.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintStream out = new PrintStream(System.out);

        a.list(out,1,1);

        out.println();

        AdminUnitList o = a.selectByName("gmina Wielka Wie", false);
        AdminUnitList o1 = a.selectByName("Wronin", false);


        System.out.println(o.getUnits().get(0).getBox().boxOnMapString());

        AdminUnit modlniczka = o.getUnits().get(0);
        AdminUnit modlnica = o1.getUnits().get(0);

        try {
            AdminUnitList somsiedzi = a.getNeighbors(modlniczka,5);
            for (AdminUnit u : somsiedzi.getUnits()){
                System.out.println(u.toString());
            }
        } catch (EmptyBoundingBoxException e) {
            e.printStackTrace();
        }


        int maxdistance=15;

        /////////czas wyszukiwania sasiadow w dwoch petlach
        /*double t1 = System.nanoTime()/1e6;
        // wywołanie funkcji
        for (AdminUnit adminUnit : a.getUnits()) {
            for (AdminUnit adminUnit1 : a.getUnits()) {
                if(adminUnit1.getAdminLevel() >= 8 ) {
                    if (adminUnit.getBox().intersectsWithMaxDistance(adminUnit1.getBox(), maxdistance)){
                        adminUnit.neighbours.add(adminUnit1);
                        adminUnit1.neighbours.add(adminUnit);
                    }
                } else if (adminUnit != adminUnit1 && adminUnit.getBox().intersects(adminUnit1.getBox())) {
                    adminUnit.neighbours.add(adminUnit1);
                    adminUnit1.neighbours.add(adminUnit);
                }
            }
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
*/
        //////czas wyszukiwania sasiadow rTree
        //tworze roota
        AdminUnit Polska = new AdminUnit("Polska", 1000, 2, 30000000, 100);
        for (AdminUnit u : b.getUnits()) {
            if (u.getAdminLevel()==4)
                Polska.getChildren().add(u);
        }
        double t11 = System.nanoTime()/1e6;
        // wywołanie funkcji
        rTreeSearch(Polska, maxdistance);
        double t22 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t22-t11);


        //lab 9
        a.filter(p->p.getName().startsWith("K")).sortInplaceByArea().list(out);
        out.println();
        a.filter(p-> p.getAdminLevel() == 6 && p.getParent().getName().contains("województwo małopolskie")).list(out);
        out.println();

        a.filter((p->true)).sortInplaceByArea().list(out);
        out.println();

    }
}
