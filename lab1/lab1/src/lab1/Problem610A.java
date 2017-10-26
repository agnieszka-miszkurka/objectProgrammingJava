package lab1;

import java.util.Locale;
import java.util.Scanner;

public class Problem610A {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int stick_length = scan.nextInt();
        int counter=0;

        if (stick_length%2==1){
            System.out.println(counter);
            return;
        }

        for(int i=1; i<=stick_length/4; i++ ) {
            counter++;
        }
        if(stick_length%4==0)
            counter--;

        System.out.println(counter);
    }

}
