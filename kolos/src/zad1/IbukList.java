package zad1;

import csvReader.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IbukList {

    List<Ibuk> ibuks = new ArrayList<>();

    void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename,";", true);
        while (reader.next()) {
            String tytul;
            String autor;
            String wydawnictwo;
            int rok_wydania;
            String kategoria;

            tytul = reader.get("Tytuł");
            autor = reader.get("Autor");
            wydawnictwo = reader.get("Wydawnictwo");
            rok_wydania = reader.getInt("Rok wydania");
            kategoria = reader.get("Kategoria");
            //System.out.println(tytul + " " + autor);

            ibuks.add(new Ibuk(tytul, autor, wydawnictwo, rok_wydania, kategoria));
        }



    }

}
