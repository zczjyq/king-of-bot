package PTAJAVA2;

import java.util.Scanner;

class PersonSortable2 {
    private String name;
    private int age;

    public PersonSortable2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "-" + age;
    }
}

public class Main4 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
    }
}
