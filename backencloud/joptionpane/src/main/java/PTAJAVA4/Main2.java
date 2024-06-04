package PTAJAVA4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        int n, count = 0;
        Scanner cin = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();
        n = cin.nextInt();
        while (count < n) {
            try {
                count ++ ;
                String s = cin.next();
                int a = Integer.parseInt(s);
                list.add(a);
            } catch (Exception e) {
                count -- ;
                System.out.println(e);
            }
        }
        System.out.println(list.toString());
    }
}
