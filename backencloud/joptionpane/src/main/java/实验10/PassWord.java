package 实验10;

import java.io.*;
import java.util.Scanner;

public class PassWord {
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        boolean success = false;
        System.out.println("请输入密码");
        String password = cin.nextLine();
        if (password.equals("tiger123")) {
            success = true;
            System.out.println("您第1次密码输入正确");
        } else {
            System.out.println("您第一次输入的密码" + password + "不对\n输入密码:");
            password = cin.nextLine();
            if (password.equals("tiger123")) {
                success = true;
                System.out.println("您第2次密码输入正确");
            }
        }
        if (success) {
            File file = new File("score.txt");
            try {
                FileReader inOne = new FileReader(file);
                BufferedReader inTwo = new BufferedReader(inOne);
                String s = null;
                while ((s = inTwo.readLine()) != null) {
                    System.out.printf(s);
                }
                inOne.close();
                inTwo.close();
            } catch (IOException exp) {

            }
        }
    }
}
