package PTAJAVA5;

import java.util.Scanner;

interface Biology {
    void breath();
}

interface Animal {
    void eat();
    void sleep();
}

class Person implements Biology, Animal {
    public void think() {
        System.out.println("我喜欢思考");
    }

    @Override
    public void breath() {
        System.out.println("我喜欢呼吸新鲜空气");
    }

    @Override
    public void eat() {
        System.out.println("我会按时吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("早睡早起身体好");
    }
}

class Pupil extends Person {
    private String school;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}

public class Main6 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        Pupil zhangsan = new Pupil();
        String str = cin.next();
        zhangsan.setSchool(str);
        System.out.println("我的学校是" + zhangsan.getSchool());
        zhangsan.breath();
        zhangsan.eat();
        zhangsan.sleep();
        zhangsan.think();
    }
}
