package PTAJAVA3;

import java.util.Scanner;

public class Main7 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String str = cin.nextLine();
        String change = cin.next();
        change = change.trim();
        String to = cin.nextLine();
        to = to.substring(1);
        String[] list = new String[1000];
        int count = 0;
        String word = "";
        for (int i = 0; i < str.length() - change.length(); i ++ ) {
            boolean flag = true;
            for (int j = i; j < i + change.length(); j ++ ) {
                if (!str.substring(j, j + 1).equals(change.substring(j - i, j - i + 1))) {
                    flag = false;
                }
            }
            if (flag) {
                if (i == str.length() - change.length() - 1) {
                    if (count != 0) list[count ++] = "";
                    list[count ++] = str.substring(i + 1);
                } else {
                    if (count != 0) list[count ++] = "";
                    list[count ++] = word;
                    word = "";
                }
            } else {
                word += str.substring(i, i + 1);
                if (i == str.length() - change.length() - 1) {
                    if (count != 0) list[count ++] = "";
                    list[count ++] = word;
                }
            }
        }
        System.out.printf("[");
        for (int i = 0; i < count; i ++ ) {
            if (list[i] == null) break;
            if (i == 0) System.out.printf("\"%s\"", list[i]);
            else System.out.printf(", \"%s\"", list[i]);
        }
        System.out.println("]");
        str = str.replaceAll(change, to);
        System.out.println(str);
    }
}
