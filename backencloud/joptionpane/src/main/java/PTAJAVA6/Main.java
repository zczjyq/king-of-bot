package PTAJAVA6;

import java.util.ArrayList;
import java.util.Scanner;

interface GeneralStack<T> {
    T push(T item);            //如item为null，则不入栈直接返回null。
    T pop();                 //出栈，如为栈为空，则返回null。
    T peek();                //获得栈顶元素，如为空，则返回null.
    public boolean empty();//如为空返回true
    public int size();     //返回栈中元素数量
}
class ArrayListGeneralStack<T> implements GeneralStack<T> {
    ArrayList<T> arrayList = new ArrayList<>();

    @Override
    public T push(T item) {
        System.out.println("push:" + item);
        if (item == null) return null;
        arrayList.add(item);
        return null;
    }

    @Override
    public T pop() {
        if (arrayList.isEmpty()) return null;
        T a = arrayList.get(arrayList.size() - 1);
        arrayList.remove(arrayList.size() - 1);
        System.out.println("pop:" + a);
        return a;
    }

    public T get(int index) {
        return arrayList.get(index);
    }

    @Override
    public T peek() {
        if (arrayList.isEmpty()) return null;
        return arrayList.get(arrayList.size() - 1);
    }

    @Override
    public boolean empty() {
        return arrayList.isEmpty();
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public String toString() {
        return arrayList + "";
    }
}
class Car {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car [" +
                "id=" + id +
                ", name=" + name +
                ']';
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String op = cin.next();
            if (op.equals("quit")) {
                break;
            }
            int m = cin.nextInt(), n = cin.nextInt();
            if (op.equals("Integer")) {
                System.out.println("Integer Test");
                ArrayListGeneralStack<Integer> stack = new ArrayListGeneralStack<>();
                for (int i = 0; i < m; i ++ ) {
                    Integer a = cin.nextInt();
                    stack.push(a);
                }
                for (int i = 0; i < n; i ++ ) {
                    stack.pop();
                }
                System.out.println(stack.toString());
                System.out.println(stack.getClass().getInterfaces()[0]);
                int sum = 0;
                for (int i = 0; i < stack.size(); i ++ ) {
                    sum += stack.get(i);
                }
                System.out.println("sum=" + sum);
            } else if (op.equals("Double")) {
                System.out.println("Double Test");
                ArrayListGeneralStack<Double> stack = new ArrayListGeneralStack<>();
                for (int i = 0; i < m; i ++ ) {
                    Double a = cin.nextDouble();
                    stack.push(a);
                }
                for (int i = 0; i < n; i ++ ) {
                    stack.pop();
                }
                System.out.println(stack.toString());
                System.out.println(stack.getClass().getInterfaces()[0]);
                double sum = 0;
                for (int i = 0; i < stack.size(); i ++ ) {
                    sum += stack.get(i);
                }
                System.out.println("sum=" + sum);
            } else {
                ArrayListGeneralStack<Car> stack = new ArrayListGeneralStack<>();
                System.out.println("Car Test");
                for (int i = 0; i < m; i ++ ) {
                    int num = cin.nextInt();
                    String name = cin.next();
                    Car car = new Car(num, name);
                    stack.push(car);
                }
                for (int i = 0; i < n; i ++ ) {
                    stack.pop();
                }
                System.out.println(stack.toString());
                for (int i = stack.size() - 1; i >= 0; i -- ) {
                    Car info = stack.get(i);
                    System.out.println(info.getName());
                }
                System.out.println(stack.getClass().getInterfaces()[0]);
            }
        }
    }
}