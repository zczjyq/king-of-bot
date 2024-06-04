package PTAJAVA3;

import java.util.*;

public class Main6 {

    public static void main(String [] args) {
        Scanner cin = new Scanner(System.in);
        Map<String, Integer> wordCount = new HashMap<>();
        while (cin.hasNext()) {
            String word = cin.next();
            if (word.equals("!!!!!")) break;
            word = word.toLowerCase();
            word = word.replaceAll("[!.,:*?]", "");

            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordCount.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue().equals(o1.getValue())) return o1.getKey().compareTo(o2.getKey());
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        System.out.println(wordCount.size());
        for (int i = 0; i < 10; i ++ ) {
            System.out.println(entries.get(i).getKey() + "=" + entries.get(i).getValue());
        }
    }
}
