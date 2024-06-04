package PTAJAVA6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Info {
    public ArrayList<Integer> nums;
    public String word;

    public Info(int nums, String word) {
        this.nums = new ArrayList<>();
        this.nums.add(nums);
        this.word = word;
    }

    public void addWord(int nums) {
        this.nums.add(nums);
    }

    @Override
    public String toString() {
        return word + "=" + nums;
    }

    public boolean check(int count) {
        return count != this.nums.get(this.nums.size() - 1);
    }
}

public class Main3 {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        ArrayList<Info> infoArrayList = new ArrayList<>();
        int count = 1;
        Scanner cin = new Scanner(System.in);
        while (true) {
            String line = cin.nextLine();
            if (line.equals("!!!!!")) break;
            arrayList.add(line);
            String[] words = line.split(" ");
            for (String word : words) {
                boolean flag = true;
                for (Info value : infoArrayList) {
                    if (word.equals(value.word)) {
                        if (value.check(count)) value.addWord(count);
                        flag = false;
                    }
                }
                if (flag) {
                    Info info = new Info(count, word);
                    infoArrayList.add(info);
                }
            }
            count ++ ;
        }
        infoArrayList.sort((o1, o2) -> o1.word.compareTo(o2.word));
        for (int i = 0; i < infoArrayList.size(); i ++ ) {
            System.out.println(infoArrayList.get(i));
        }

        while (cin.hasNext()) {
            String words = cin.nextLine();
            String[] finds = words.split(" ");
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 1; i < arrayList.size(); i ++ ) {
                boolean flag = true;

                for (String find : finds) {
                    if (!arrayList.get(i).contains(find)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ans.add(i);
                }
            }
            if (ans.isEmpty()) System.out.println("found 0 results");
            else {
                System.out.println(ans);
                for (int value : ans) {
                    System.out.println("line " + value + ":" + arrayList.get(value));
                }
            }
        }
    }
}
