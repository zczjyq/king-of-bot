package PTAJAVA6;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Main6 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, Integer> map = new TreeMap<String, Integer>();
        String str;
        int[] book = new int[200];

        while (true) {
            str = input.nextLine();
            if (str.equals("0000")) break;
            str = str.toUpperCase();

            for (int i = 0; i < str.length(); i++)
                book[str.charAt(i)]++;

            String[] temp = str.split(" ");

            for (int i =0; i < temp.length; i++) {
                if (map.containsKey(temp[i]))
                    map.put(temp[i],map.get(temp[i]) + 1);
                else
                    map.put(temp[i], 1);
            }
        }
        String text_Max = null, text_Min = null;
        int Max = 0, Min = 100000000;
        Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> temp = it.next();
            String key = temp.getKey();
            int value = temp.getValue();
            if (value >= Max) {
                Max = value;
                text_Max = key;
            }
            if (value < Min) {
                Min =  value;
                text_Min = key;
            }
        }
        System.out.printf("%-10s%8d\n", text_Max, Max);
        System.out.printf("%-10s%8d\n", text_Min, Min);
        for (int i = 'A'; i  <= 'Z'; i++)
            System.out.printf("%-10c%8d\n", i, book[i]);
        input.close();
    }
}