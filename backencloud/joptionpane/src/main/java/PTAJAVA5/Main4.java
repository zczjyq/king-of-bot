package PTAJAVA5;
import java.util.*;
class Rect{
    double l,h,z;
    Rect(double l,double h,double z){
        this.l=l;
        this.h=h;
        this.z=z;
    }
}
class Cubic extends Rect{
    Cubic(double l,double h,double z){
        super(l,h,z);
    }
    double length(double l,double h,double z){
        return 2*(l*h+l*z+z*h);
    }
    public double area(double l,double h,double z){
        return l*h*z;
    }
}
class Pyramid extends Rect{
    Pyramid(double l,double h,double z){
        super(l,h,z);
    }
    double length(double l,double h,double z){
        return l*h+l*Math.sqrt(z*z+h*h/4.0)+h*Math.sqrt(z*z+l*l/4.0);
    }
    public double area(double l,double h,double z){
        return l*h*z*1.0/3;
    }
}
public class Main4{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        while (n>0){
            double x= in.nextDouble();
            double y= in.nextDouble();
            double z= in.nextDouble();
            if (x<=0||y<=0||z<=0) {x=0;y=0;z=0;}
            Cubic p=new Cubic(x,y,z);
            Pyramid p1=new Pyramid(x,y,z);
            System.out.printf("%.2f %.2f %.2f %.2f\n",
                    p.length(x,y,z),p.area(x,y,z),
                    p1.length(x,y,z),p1.area(x,y,z));
            n--;
        }

    }
}
