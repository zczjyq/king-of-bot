package PTAJAVA5;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int sum = 0;
        String str = cin.nextLine();
        String[] strs = str.split(";");
        for (int i = 0; i < strs.length; i ++ ) {
            String infos[] = strs[i].split(":");
            infos[0] = infos[0].trim();
            infos[1] = infos[1].trim();
            System.out.println(infos[0] + "--" + infos[1]);
            sum += Integer.parseInt(infos[1]);
        }
        System.out.println("总价格--" + sum);
    }
}
