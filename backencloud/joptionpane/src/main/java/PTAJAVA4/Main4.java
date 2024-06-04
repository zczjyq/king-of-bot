package PTAJAVA4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Animal{
    private String name;
    private int age;

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

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Bird extends Animal {
    public Bird(String name, int age) {
        super(name, age);
    }
}

class Dog extends Animal {
    private int leg;

    public int getLeg() {
        return leg;
    }

    public void setLeg(int leg) {
        this.leg = leg;
    }

    public Dog(String name, int age, int leg) {
        super(name, age);
        this.leg = leg;
    }

    @Override
    public String toString() {
        return "Dog[" +
                "leg=" + leg +
                '}';
    }
}

public class Main4 {
    public static void main(String args[]) {
        List<Animal> list = new ArrayList<>();
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        for (int i = 0; i < n; i ++ ) {
            int x = cin.nextInt();
            if (x == 1) {
                String name = cin.next();
                int age = cin.nextInt(), leg = cin.nextInt();
                Dog dog = new Dog(name, age, leg);
                list.add(dog);
            } else {
                String name = cin.next();
                int age = cin.nextInt();
                Bird bird = new Bird(name, age);
                list.add(bird);
            }
        }
        for (int i = 0; i < list.size(); i ++ ) {
            System.out.println(list.get(i).toString());
        }
    }
}
