package zad3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem115A {

    public static void main(String [] args){
        String [] tab = {"a", "aga","ada", "eigi", "ee", "Kama", "mama", "Milek", "tata"};
        Arrays.asList(tab).sort((a, b)->Integer.compare(a.length(),b.length()));
        Arrays.stream(tab).forEach(System.out::println);
        Arrays.asList(tab).sort((a, b)->Integer.compare(-a.length(),-b.length()));
        Arrays.stream(tab).forEach(System.out::println);
        System.out.println();
        Arrays.asList(tab).sort((a, b)->Integer.compare(a.toLowerCase().charAt(0),b.toLowerCase().charAt(0)));
        Arrays.stream(tab).forEach(System.out::println);
        System.out.println();
        Arrays.asList(tab).sort((a, b)->{
            return a.charAt(0)==101 || b.charAt(0)==101 ? a.charAt(0)==b.charAt(0) ? 0 : a.charAt(0)==101 ? -1 : 1 : 0;
        });
        Arrays.stream(tab).forEach(System.out::println);

        System.out.println();
        Arrays.asList(tab).sort(Problem115A::oo);
        Arrays.stream(tab).forEach(System.out::println);


    }

    static int oo (String a , String b ) {
        return a.charAt(0)==101 || b.charAt(0)==101 ? a.charAt(0)==b.charAt(0) ? 0 : a.charAt(0)==101 ? -1 : 1 : 0;
    }
}
