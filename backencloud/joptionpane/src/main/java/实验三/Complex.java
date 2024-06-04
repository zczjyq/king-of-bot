package 实验三;

import java.util.Scanner;

public class Complex {
    private int xu;
    private int shi;

    public Complex(int shi, int xu) {
        this.xu = xu;
        this.shi = shi;
    }

    public int getXu() {
        return xu;
    }

    public void setXu(int xu) {
        this.xu = xu;
    }

    public int getShi() {
        return shi;
    }

    public void setShi(int shi) {
        this.shi = shi;
    }

    @Override
    public String
    toString() {
        return "Complex[" +
                "实部=" + shi +
                ",虚部=" + xu +
                ']';
    }
}

class Main {
    public static Complex add(Complex a, Complex b) {
        return new Complex(a.getShi() + b.getShi(),
                a.getXu() + b.getXu());
    }

    public static Complex mul(Complex a, Complex b) {
        return new Complex(a.getShi() * b.getShi() - a.getXu() * b.getXu(),
                a.getShi() * b.getXu() + a.getXu() + b.getShi());
    }
    public static void main(String[] agrs) {
        Scanner cin = new Scanner(System.in);
        int s1 = cin.nextInt(), n1 = cin.nextInt();
        Complex complex1 = new Complex(s1, n1);
        int s2 = cin.nextInt(), n2 = cin.nextInt();
        Complex complex2 = new Complex(s2, n2);
        System.out.println("相加结果:" + add(complex1, complex2));
        System.out.println("相乘结果:" + mul(complex1, complex2));
    }
}