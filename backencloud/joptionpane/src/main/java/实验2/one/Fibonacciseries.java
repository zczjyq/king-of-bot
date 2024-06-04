package 实验2.one;

import java.util.Scanner;

public class Fibonacciseries {
    public static int fib(int u) {
        if (u == 1 || u == 0) return u;
        else return fib(u -  1) + fib(u - 2);
    }
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        System.out.println(fib(n));
    }
}
