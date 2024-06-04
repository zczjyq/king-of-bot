package PTAJAVA5;

import java.util.Scanner;

public class Main3 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int a = 0, b = 0;
        try {
            a = in.nextInt();
            b = in.nextInt();
        } catch (Exception e) {
            System.out.println("the input is not numerical");
            System.exit(0);
        }
        if (b == 0) {
            System.out.println("zero division error");
            System.exit(0);
        }
        System.out.printf("%.2f",a*1.0/b);
    }
}
