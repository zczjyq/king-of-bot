package PTAJAVA4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main7 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        Pattern p = Pattern.compile("[0-9]+");
        int sum;
        while(cin.hasNext()) {
            String line = cin.nextLine();
            if (line.equals("end")) System.exit(0);
            Matcher m = p.matcher(line);
            sum = 0;
            while (m.find()) {
                sum += Integer.valueOf(m.group());
            }
            System.out.println(sum);
        }
    }
}
