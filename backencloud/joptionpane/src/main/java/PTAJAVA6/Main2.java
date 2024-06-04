package PTAJAVA6;

import com.sun.jdi.connect.spi.TransportService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

interface IntegerStack {
    public Integer push(Integer item);            //如item为null，则不入栈直接返回null。
    public Integer pop();                 //出栈，如为栈为空，则返回null。
    public Integer peek();                //获得栈顶元素，如为空，则返回null.
    public boolean empty();//如为空返回true
    public Integer size();     //返回栈中元素数量

}

class ArrayListIntegerStack implements IntegerStack {
    private LinkedList<Integer> linkedList;
    public ArrayListIntegerStack() {
        this.linkedList = new LinkedList<Integer>();
    }

    @Override
    public Integer push(Integer item) {
        if (item == null) return null;
        this.linkedList.add(item);
        return item;
    }

    @Override
    public Integer pop() {
        if (linkedList.isEmpty()) {
            return null;
        }
        Integer a = linkedList.get(linkedList.size() - 1);
        linkedList.pollLast();
        return a;
    }

    @Override
    public Integer peek() {
        if (linkedList.isEmpty()) {
            return null;
        } else {
            return linkedList.get(linkedList.size() - 1);
        }
    }

    @Override
    public boolean empty() {
        return linkedList.isEmpty();
    }

    @Override
    public Integer size() {
        return linkedList.size();
    }

    @Override
    public String toString() {
        return linkedList + "";
    }
}

public class Main2 {
    public static void main(String[] args) {
        ArrayListIntegerStack arrayListIntegerStack = new ArrayListIntegerStack();
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        for (int i = 0; i < n; i ++ ) {
            int a = cin.nextInt();
            System.out.println(arrayListIntegerStack.push(a));
        }
        System.out.println(arrayListIntegerStack.peek()
                + "," + arrayListIntegerStack.empty()
                + "," + arrayListIntegerStack.size());
        System.out.println(arrayListIntegerStack.toString());
        int m = cin.nextInt();
        for (int i = 0; i < m; i ++ ) {
            System.out.println(arrayListIntegerStack.pop());
        }
        System.out.println(arrayListIntegerStack.peek()
                + "," + arrayListIntegerStack.empty()
                + "," + arrayListIntegerStack.size());
        System.out.println(arrayListIntegerStack.toString());
    }
}