import java.util.Scanner;

public class Main {
    public static class Circle {
        private double radius;
        Circle(double radius) {
            this.radius = radius;
        }
        Circle() {}
        void setRadius(double r) {
            this.radius = r;
        }
        double getRadius() {
            return radius;
        }
        public double getArea(){
            return Math.PI*radius*radius;
        }
        double getPerimeter() {
            return radius * 2 * Math.PI;
        }
        @Override
        public String toString() {
            return "Circle(r:" + this.radius + ")";
        }
    }
    public static class Cylinder {
        private double height;
        private Circle circle;
        public Cylinder(double height, Circle circle) {
            this.height = height;
            this.circle = circle;
        }
        public Cylinder() {
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public Circle getCircle() {
            return circle;
        }

        public void setCircle(Circle circle) {
            this.circle = circle;
        }

        double getArea() {
            return circle.getRadius() * circle.getRadius() * 2 * Math.PI + circle.getPerimeter() * this.height;
        }

        double getVolume() {
            return circle.getRadius() * circle.getRadius() * 2 * Math.PI * this.height;
        }

        @Override
        public String toString() {
            return "Cylinder(" +
                    "h:" + height +
                    ",Circle(r:" + circle.getRadius() +
                    "))";
        }
    }
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for(int i = 0; i < n; i++) {
            String str = input.next();
            if(str.equals("Circle")) {
                Circle c = new Circle(input.nextDouble());
                System.out.println("The area of " + c.toString() + " is " + String.format("%.2f",c.getArea()));
                System.out.println("The perimeterof " + c.toString() + " is "+ String.format("%.2f",c.getPerimeter()));
            } else if(str.equals("Cylinder")) {
                Cylinder r = new Cylinder(input.nextDouble(), new Circle(input.nextDouble()));
                System.out.println("The area of " + r.toString() + " is " + String.format("%.2f",r.getArea()));
                System.out.println("The volume of " + r.toString() + " is " + String.format("%.2f",r.getVolume()));
            }
        }
    }
}