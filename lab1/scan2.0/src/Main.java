import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args){
        System.out.print("bluh");
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Wczytano %s , %d, %f",s,i,d);
    }
}