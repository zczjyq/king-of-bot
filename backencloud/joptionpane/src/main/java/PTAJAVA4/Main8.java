package PTAJAVA4;

import java.util.Scanner;

class ArrayUtils {
    public static double findMax(double[] arr,int begin, int end) throws IllegalArgumentException  {
        if (begin < 0) {
            throw new IllegalArgumentException("begin:" + begin + " < 0 ");
        }
        if (end > arr.length) {
            throw new IllegalArgumentException("end:" + end + " > arr.length");
        }
        if (begin >= end) {
            throw new IllegalArgumentException("begin:" + begin + " >= end:" + end);
        }
        double max = -102030293;
        for (int i = begin; i < end; i ++ ) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}

public class Main8 {
    public static void main(String[] args) throws Exception {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        double[] arrs = new double[n];
        for (int i = 0; i < n; i ++ ) {
            arrs[i] = cin.nextInt();
        }
        String line1 = cin.nextLine();
        while (cin.hasNext()) {
            String line = cin.nextLine();
            if (line.equals("end")) {
                break;
            } else {
                String[] infos = line.split(" ");
                int begin = Integer.parseInt(infos[0]);
                int end = Integer.parseInt(infos[1]);
                try {
                    System.out.println(ArrayUtils.findMax(arrs, begin, end));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        try {
            System.out.println(ArrayUtils.class.getDeclaredMethod("findMax", double[].class,int.class,int.class));
        } catch (Exception e1) {
        }
    }
}
