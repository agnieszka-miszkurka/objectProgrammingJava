package Admin;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.Assert.*;

public class AdminUnitQueryTest {
    @Test
    public void limit() throws Exception {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintStream out = new PrintStream(System.out);

        AdminUnitList list = a;

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(el -> el.getParent().getName().contains("Wielka Wie"))
                .or(el -> el.getName().contains("Krak"))
                .sort((el1, el2) -> (el1.getName().compareTo(el2.getName())))
                .limit(10);
        assertEquals(query.execute().getUnits().size(), 10);
    }

    @Test
    public void execute() throws Exception {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintStream out = new PrintStream(System.out);

        AdminUnitList list = a;

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(el -> el.getParent().getName().contains("Wielka Wie"))
                .or(el -> el.getName().contains("Krak"))
                .sort((el1, el2) -> (el1.getName().compareTo(el2.getName())))
                .limit(10);
        assertNotEquals(query.execute().getUnits().size(), a.getUnits().size());
    }

}