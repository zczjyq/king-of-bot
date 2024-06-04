package 实验2.one;

public class Narcissistic {
    public static int check(int u) {
        int res = 0;
        while (u != 0) {
            int y = u % 10;
            u /= 10;
            res += y * y * y;
        }
        return res;
    }
    public static void main(String args[]) {
        for (int i = 100; i <= 1000; i ++ )
            if (check(i) == i)
                System.out.println(i);
    }
}
