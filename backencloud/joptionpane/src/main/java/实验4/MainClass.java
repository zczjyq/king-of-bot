package 实验4;

abstract class Geometry  {
    public abstract double getArea();
}
class TotalArea {
    Geometry[] tuxing;
    double totalArea=0;
    public void setTuxing(Geometry[] t) {
        tuxing=t;
    }
    public double computerTotalArea() {
        //用循环语句让tuxing的元素调用getArea方法，并将返回的值累加到totalArea
        for(Geometry i : tuxing) {
            totalArea += i.getArea();
        }
        return totalArea;
    }
}

class Rect extends Geometry {
    double a,b;
    Rect(double a,double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getArea() {
        return a * b;
    }
   //重写 getArea()方法
}
class Circle extends Geometry {
    double r;
    Circle(double r) {
        this.r = r;
    }

    @Override
    public double getArea() {
        return r * r * Math.PI;
    }
   //重写 getArea()方法
}
public class MainClass{
    public static void main(String args[]) {
        Geometry[] tuxing = new Geometry[29]; //有29个Geometry对象
        for (int i = 0; i < tuxing.length; i++) {   //29个Geometry对象分成两类
            if (i % 2 == 0)
                tuxing[i] = new Rect(16 + i, 68);
            else if (i % 2 == 1)
                tuxing[i] = new Circle(10 + i);
        }
        TotalArea computer = new TotalArea();
        computer.setTuxing(tuxing);
        System.out.printf("各种图形的面积之和:\n%f", computer.computerTotalArea());
    }
}