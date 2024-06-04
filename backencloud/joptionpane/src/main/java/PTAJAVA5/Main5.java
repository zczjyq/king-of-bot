package PTAJAVA5;

import java.util.Scanner;

class Student {
    protected String name;
    protected int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void show() {
        System.out.println("Student[name=" + this.name + ",age=" + this.age + "]");
    }
}

class Undergraduate extends Student {
    private String major;
    public Undergraduate(String name, int age, String major) {
        super(name, age);
        this.major = major;
    }

    public void show() {
        System.out.println("Undergraduate[name=" + this.name +
                ",age=" + this.age + ",major=" + this.major + "]");
    }
}

public class Main5 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String name = cin.next();
        int age = cin.nextInt();
        Student student = new Student(name, age);

        name = cin.next();
        age = cin.nextInt();
        String major = cin.next();
        Undergraduate undergraduate = new Undergraduate(name, age, major);
        student.show();
        undergraduate.show();
    }
}
