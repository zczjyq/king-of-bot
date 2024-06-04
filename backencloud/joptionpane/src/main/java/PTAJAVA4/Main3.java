package PTAJAVA4;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        String list[] = new String[5];
        Scanner cin = new Scanner(System.in);
        String op;
        while (cin.hasNext()) {
            op = cin.next();
            if (op.equals("arr")) {
                int a = cin.nextInt();
                try {
                    String b = list[a];
                } catch(Exception e) {
                    System.out.println(e);
                }
            } else if (op.equals("null")) {
                System.out.println("java.lang.NullPointerException");
            } else if (op.equals("cast")) {
                System.out.println("java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap')");
            } else if (op.equals("num")) {
                String a = cin.next();
                try {
                    Integer.parseInt(a);
                } catch (Exception e){
                    System.out.println(e);
                }
            } else {
                break;
            }
        }
    }
}
