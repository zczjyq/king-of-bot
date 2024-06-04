package PTAJAVA3;

import java.util.*;

class PersonOverride extends Object {
    private String name;
    private int age;
    private boolean gender;

    public PersonOverride() {
        this("default", 1, true);
    }

    public PersonOverride(String name, int age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    @Override
    public String toString() {
        return name + '-' + age + "-" + gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonOverride that = (PersonOverride) o;
        return age == that.age && gender == that.gender && Objects.equals(name, that.name);
    }
}

public class Main {


    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        List<PersonOverride> personOverrideList = new ArrayList<>(100);
        int n1 = cin.nextInt();
        for (int i = 0; i < n1; i ++ ) {
            PersonOverride personOverride = new PersonOverride();
            personOverrideList.add(personOverride);
        }
        int n2 = cin.nextInt();
        for (int i = 0; i < n2; i ++ ) {
            String name = cin.next();
            int age = cin.nextInt();
            boolean st = cin.nextBoolean();
            PersonOverride personOverride = new PersonOverride(name, age, st);
            boolean check = false;
            for (int j = 0; j < personOverrideList.size(); j ++ ) {
                if (personOverride.equals(personOverrideList.get(j))) check = true;
            }
            if (!check) personOverrideList.add(personOverride);
        }
        for (int i = 0; i < personOverrideList.size(); i ++ ) {
            System.out.println(personOverrideList.get(i).toString());
        }
        System.out.println(personOverrideList.size() - n1);
        System.out.println(Arrays.toString(PersonOverride.class.getConstructors()));
    }
}
