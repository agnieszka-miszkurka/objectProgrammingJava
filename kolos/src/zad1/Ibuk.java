package zad1;

import csvReader.CSVReader;

import java.io.IOException;

public class Ibuk {

    String tytul;
    String autor;
    String wydawnictwo;
    int rok_wydania;
    String kategoria;


    public Ibuk(String tytul, String autor, String wydawnictwo, int rok_wydania, String kategoria) {
        this.tytul = tytul;
        this.autor = autor;
        this.wydawnictwo = wydawnictwo;
        this.rok_wydania = rok_wydania;
        this.kategoria = kategoria;
    }
}
