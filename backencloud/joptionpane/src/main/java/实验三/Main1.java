package 实验三;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int a = cin.nextInt(), b = cin.nextInt(), c = cin.nextInt();
        Equation equation = new Equation(a, b, c);
        equation.slove();
    }
}
