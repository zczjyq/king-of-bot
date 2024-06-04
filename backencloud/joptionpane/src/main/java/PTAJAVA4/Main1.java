package PTAJAVA4;

import java.util.*;

class Person {
    private String num;
    private String name;
    private int age;
    private String gender;

    public Person(String num, String name, int age, String gender) {
        this.num = num;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public int hashCode() {
        return Objects.hash(num, name, age, gender);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age &&
                Objects.equals(num, person.num) &&
                Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender);
    }
    @Override
    public String toString() {
        return num + " " + name + " " + age +
                " " + gender;
    }
}

public class Main1 {
    public static void main(String[] args) {
        Map<String, Person> personSet = new HashMap<>();
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        for (int i = 0; i < n; i ++ ) {
            String num = cin.next(), name = cin.next();
            int age = cin.nextInt();
            String gender = cin.next();
            Person person = new Person(num, name, age, gender);
            personSet.put(num, person);
        }
        List<Person> personList = new ArrayList<>();
        for (String s:personSet.keySet()) {
            Person person = personSet.get(s);
            personList.add(person);
        }
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getNum().compareTo(o2.getNum());
            }
        });
        System.out.println(personList.size());
        for (int i = 0; i < personList.size(); i ++ ) {
            System.out.println(personList.get(i));
        }
    }
}
