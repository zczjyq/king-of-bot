package 实验2.one;

import java.util.Scanner;

public class Fibonacciseries1 {
    public static int []dp = new int[100];
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i ++ ) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}
