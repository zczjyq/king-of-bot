package PTAJAVA2;


import java.util.Scanner;

public class Main {
    public static class Ring {
        private int innerRadius;
        private int outerRadius;

        Ring(int innerRadius, int outerRadius) {
            this.innerRadius = innerRadius;
            this.outerRadius = outerRadius;
            System.out.println("constructor");
        }

        Ring() {
            this.innerRadius = 1;
            this.outerRadius = 3;
        }
        public void setInnerRadius(int innerRadius) {
            this.innerRadius = innerRadius;
        }
        public void setOuterRadius(int outerRadius) {
            this.outerRadius = outerRadius;
        }
        public int getOuterRadius() {
            return outerRadius;
        }
        public int getInnerRadius() {
            return innerRadius;
        }
        public int getArea() {
            return (int)(Math.PI * (this.outerRadius * this.outerRadius - this.innerRadius * this.innerRadius));
        }
    }
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int a = cin.nextInt();
        int b = cin.nextInt();
        Ring ring = new Ring(a, b);
        System.out.println(ring.getArea());
        Ring ring1 = new Ring();
        System.out.println(ring1.getArea());
    }
}