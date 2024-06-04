package 实验2.one;

public class Asterisk {
    public static void main(String args[]) {
        for (int i = 0; i < 5; i ++ ) {
            for (int j = 0; j < i; j++)
                System.out.printf(" ");
            for (int k = 0; k < 5; k++)
                System.out.printf("*  ");
            System.out.println();
        }
    }
}
