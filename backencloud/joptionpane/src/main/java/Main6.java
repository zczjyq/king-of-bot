import java.util.ArrayList;
import java.util.Scanner;

abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
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
        return "Animal[" +
                "name='" + name + '\'' +
                ", age=" + age +
                ']';
    }

    public String play() {
        return "Dog";
    }
}

class Bird extends Animal{
    public Bird(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Bird[name=" + this.getName() + ", age=" + this.getAge() + "]";
    }

    public String play() {
        return "Bird";
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
        return "Dog[leg=" + this.leg + ", name=" + this.getName() + ", age=" + this.getAge() + "]";
    }

    public String play() {
        return "Dog";
    }
}

public class Main6 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        ArrayList<Animal> animals = new ArrayList<>();
        for (int i = 0; i < n; i ++ ) {
            int op = cin.nextInt();
            if (op == 1) {
                String name = cin.next();
                int age = cin.nextInt();
                int leg = cin.nextInt();
                Dog dog = new Dog(name, age, leg);
                animals.add(dog);
            } else {
                String name = cin.next();
                int age = cin.nextInt();
                Bird bird = new Bird(name, age);
                animals.add(bird);
            }
        }
        System.out.print("[");
        for (int i = 0; i < n; i ++ ) {
            if (i != 0) System.out.print(", ");
            System.out.print(animals.get(i));
        }
        System.out.print("]");

        String find = cin.next();
        boolean flag = true;
        for (int i = 0; i < n; i ++ ) {
            String name = animals.get(i).getName();
            if (name.equals(find)) {
                System.out.println(animals.get(i).play());
                System.out.println(animals.get(i).toString());
                flag = false;
            }
        }
        if (flag) {
            System.out.println("no this one");
        }
    }
}
