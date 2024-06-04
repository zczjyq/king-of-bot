package PTAJAVA3;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class PersonSortable2 {
    private String name;
    private int age;

    public PersonSortable2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "-" + age;
    }
}

class NameComparator implements Comparator<PersonSortable2> {
    public int compare(PersonSortable2 o1, PersonSortable2 o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class AgeComparator implements Comparator<PersonSortable2>{

    @Override
    public int compare(PersonSortable2 o1, PersonSortable2 o2) {
        return o1.getAge() - o2.getAge();
    }
}

public class Main4 {
    public static void main(String[] agrs) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        PersonSortable2[] personSortable2s = new PersonSortable2[n];
        for (int i = 0; i < n; i ++ ) {
            String name = cin.next();
            int age = cin.nextInt();
            personSortable2s[i] = new PersonSortable2(name, age);
        }
        System.out.println("NameComparator:sort");
        Arrays.sort(personSortable2s, new NameComparator());
        for (int i = 0; i < n; i ++ ) {
            System.out.println(personSortable2s[i]);
        }
        System.out.println("AgeComparator:sort");
        Arrays.sort(personSortable2s, new AgeComparator());
        for (int i = 0; i < n; i ++ ) {
            System.out.println(personSortable2s[i]);
        }
    }
}
