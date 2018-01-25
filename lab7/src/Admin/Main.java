package Admin;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

public class Main {


    /*static void rTreeSearch(AdminUnit root, int maxdistance){
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
    }*/


    public static void main(String[] args) throws EmptyBoundingBoxException {
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

        a.list(out, 1, 1);

        out.println();

        AdminUnitList o = a.selectByName("Modlniczka", false);
        System.out.println(o.getUnits().get(0).getParent());
        AdminUnitList o1 = a.selectByName("Szyce", false);


        System.out.println(o.getUnits().get(0).getBox().boxOnMapString());

        AdminUnit modlniczka = o.getUnits().get(0);
        AdminUnit modlnica = o1.getUnits().get(0);

        try {
            AdminUnitList somsiedzi = a.getNeighbors(modlniczka, 5);
            for (AdminUnit u : somsiedzi.getUnits()) {
                System.out.println(u.toString());
            }
        } catch (EmptyBoundingBoxException e) {
            e.printStackTrace();
        }


        int maxdistance = 15;

        /////////czas wyszukiwania sasiadow w dwoch petlach
        double t1 = System.nanoTime() / 1e6;
        // wywołanie funkcji
        b.getNeighbors(modlniczka, maxdistance);
        double t2 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "t2-t1=%f\n", t2 - t1);

        //////czas wyszukiwania sasiadow rTree
        //towrze roota
        AdminUnit Polska = new AdminUnit("Polska", 1000, 2, 30000000, 100);
        for (AdminUnit u : a.getUnits()) {
            if (u.getAdminLevel() == 4) {
                u.setParent(Polska);
                Polska.getChildren().add(u);
            }
        }
        double t11 = System.nanoTime() / 1e6;
        // wywołanie funkcji
        a.rTreeSearch(2, modlniczka);
        double t22 = System.nanoTime() / 1e6;
        for (AdminUnit n : modlniczka.neighbours) {
            System.out.println(n.toString());
        }
        System.out.printf(Locale.US, "t2-t1=%f\n", t22 - t11);


        //lab 9
        //a.filter(p->p.getName().startsWith("K")).sortInplaceByArea().list(out);
        //out.println();
        System.out.println("$$$$$$$$$$$$$$$$$$$$4");
        a.filter(p -> p.getAdminLevel() == 6 && p.getParent().getName().contains("województwo małopolskie"), 3).list(out);
        out.println();
        a.filter(p -> p.getAdminLevel() == 6 && p.getParent().getName().contains("województwo małopolskie"), 1, 2).list(out);
        out.println();

        //a.filter((p->true)).sortInplaceByArea().list(out);
        //out.println();

        AdminUnitList list = a;

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(el -> el.getParent().getName().contains("Wielka Wie"))
                .or(el -> el.getName().contains("Krak"))
                .sort((el1, el2) -> (el1.getName().compareTo(el2.getName())))
                .limit(20);
        query.execute().list(out);

    }
}
