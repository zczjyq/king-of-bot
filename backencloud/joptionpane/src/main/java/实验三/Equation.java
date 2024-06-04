package 实验三;

public class Equation {
    private int a, b, c;

    public Equation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    void slove() {
        double x1 = (-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a;
        double x2 = (-b - Math.sqrt(b * b - 4 * a * c)) / 2 * a;
        System.out.printf("方程的解为%.2f  %.2f", x1, x2);
    }

    @Override
    public String toString() {
        return "Equation[" +
                a +
                "x^2+" + b +
                "x+" + c +
                ']';
    }
}
