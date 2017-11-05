package lab1;

import java.util.Locale;
import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        System.out.println("podaj liczbe z zakresu 1 - 45 ");
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);

        int n = scan.nextInt();
        if (n>45 || n<1){
            System.out.println("zly zakres");
        } else {
            int[] tab = new int[n];
            tab[0]=1;
            tab[1]=1;
            for (int i=2; i<n; i++) {
                tab[i] = tab[i-1] + tab[i-2];
            }
            for (int i : tab) {
                System.out.println(i);
            }
        }
    }
}
