package zad1;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        IbukList a = new IbukList();
        try {
            a.read("ibuk_wykaz_pozycji.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, Integer> lata_ksiazki = new TreeMap<>();
        for (Ibuk i : a.ibuks) {
            if(i.rok_wydania != -1) { //podano rok wydania
                if (lata_ksiazki.containsKey(i.rok_wydania)) {
                    Integer liczba_ksiazek = lata_ksiazki.get(i.rok_wydania)  + 1;
                    lata_ksiazki.put(i.rok_wydania, liczba_ksiazek);
                } else {
                    lata_ksiazki.put(i.rok_wydania, 1);
                }
            }
        }


        System.out.println(lata_ksiazki.toString());

        System.out.println();
        System.out.println("Wszystkie książki wydane przez Wydawnictwo Naukowe PWN ");
       a.ibuks.stream().forEach(b->{if (b.wydawnictwo.equals("Wydawnictwo Naukowe PWN"))
            System.out.println(b.autor + "  wydawnictwo: " + b.wydawnictwo);});

        System.out.println();
        System.out.println("ile książek należy do każdej z kategorii ");
        Map<String, Integer> kategorie_ksiazki = new HashMap<>();
        a.ibuks.stream().forEach(b->{ if (kategorie_ksiazki.containsKey(b.kategoria)) {
            Integer liczba_ksiazek = kategorie_ksiazki.get(b.kategoria)  + 1;
            kategorie_ksiazki.put(b.kategoria, liczba_ksiazek);
        } else {
            kategorie_ksiazki.put(b.kategoria, 1);
        }
         });

        System.out.println(kategorie_ksiazki.toString());


    }
}
