package PTAJAVA5;

import java.math.BigInteger;
import java.util.Scanner;

public class Main8 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String a = cin.next();
        BigInteger ab = new BigInteger(a);
        while (cin.hasNext()) {
            String b = cin.next();
            if (b.equals("e")) {
                break;
            }
            BigInteger bb = new BigInteger(b);
            ab = ab.add(bb);
        }
        System.out.println(ab);
    }
}
