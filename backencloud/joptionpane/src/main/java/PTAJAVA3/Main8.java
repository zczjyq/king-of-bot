package PTAJAVA3;

import java.util.Scanner;

public class Main8 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String str = cin.nextLine();
        String change = cin.next();

        change = change.trim();
        String to = cin.nextLine();
        to = to.substring(1);

        String[] list = str.split(change);


        System.out.print("[");
        for (int i = 0; i < list.length; i ++ ) {
            if (list[i] == null) break;
            if (i == 0) System.out.printf("\"%s\"", list[i]);
            else System.out.printf(", \"%s\"", list[i]);
        }
        System.out.println("]");
        str = str.replaceAll(change, to);
        System.out.println(str);
    }
}
