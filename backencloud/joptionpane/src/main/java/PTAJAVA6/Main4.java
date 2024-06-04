package PTAJAVA6;

import java.util.HashMap;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String str = cin.next();
            if (str.equals("!!!!!")) break;
            str = str.toLowerCase();
            hashMap.put(str, 1);
        }
        System.out.println(hashMap.size());
    }
}
