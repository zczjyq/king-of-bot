package PTAJAVA2;

import java.util.Arrays;
import java.util.Scanner;

abstract class Shape {
    static final double PI = 3.14;

    public abstract double getPerimeter();

    public abstract double getArea();
}

class Rectangle extends Shape {
    public int width, length;

    public Rectangle(int width, int length) {
        this.width = width;
        this.length = length;
    }

    @Override
    public double getPerimeter() {
        return 2 * (this.width + this.length);
    }

    @Override
    public double getArea() {
        return this.width * this.length;
    }

    @Override
    public String toString() {
        return "Rectangle [width=" + width + ", length=" + length + "]";

    }
}
class Circle extends Shape {
    public int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * this.radius * PI;
    }

    @Override
    public double getArea() {
        return this.radius * this.radius * PI;
    }
    @Override
    public String toString() {
        return "Circle [radius=" + radius + "]";
    }

}

public class Main2 {
    static double sumAllArea(Shape[] shapes) {
        double res = 0;
        for (Shape s : shapes) {
            res += s.getArea();
        }
        return res;
    }

    static double sumAllPerimeter(Shape[] shapes) {
        double res = 0;
        for (Shape s : shapes) {
            res += s.getPerimeter();
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        Shape [] shapeList = new Shape[n];
        for (int i = 0; i < n; i++) {
            String op = cin.next();
            if (op.equals("rect")) {
                int a = cin.nextInt(), b = cin.nextInt();
                Shape shape = new Rectangle(a, b);
                shapeList[i] = shape;
            } else {
                int a = cin.nextInt();
                Shape shape = new Circle(a);
                shapeList[i] = shape;
            }
        }

        System.out.println(sumAllPerimeter(shapeList));
        System.out.println(sumAllArea(shapeList));
        System.out.println(Arrays.toString(shapeList));
        for(Shape s:shapeList) {
            System.out.println(s.getClass()+","+s.getClass().getSuperclass());
        }
    }
}