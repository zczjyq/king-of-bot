import java.util.Scanner;

public class cin {
    public static void main(String[] args) {
        System.out.println("请输入学号后两位和年龄");
        Scanner cin = new Scanner(System.in);
        int a = cin.nextInt();
        int b = cin.nextInt();
        System.out.println(a + b);
    }
}