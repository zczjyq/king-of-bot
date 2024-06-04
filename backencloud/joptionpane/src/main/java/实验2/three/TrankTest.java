package 实验2.three;

import java.util.Scanner;

public class TrankTest {
    public static void main(String args[]) {
        Tank tank1 = new Tank(10);
        Tank tank2 = new Tank(1);
        Scanner cin = new Scanner(System.in);
        System.out.println("请输入两个数字用于加速");
        double s1 = cin.nextDouble();
        double s2 = cin.nextDouble();
        tank1.speedUp(s1); tank2.speedUp(s2);
        tank1.fire();
        tank2.fire();
        System.out.println("tank1" + tank1.toString() + "  tank2" + tank2.toString());
        System.out.println("请输入两个数字用于减速");
        double d1 = cin.nextDouble();
        double d2 = cin.nextDouble();
        tank1.speedDown(d1); tank2.speedDown(d2);
        tank1.fire();
        tank2.fire();
        System.out.println("tank1" + tank1.toString() + "  tank2" + tank2.toString());
    }
}
