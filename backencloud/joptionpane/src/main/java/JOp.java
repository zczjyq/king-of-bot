import javax.swing.*;

public class JOp {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "请输入一个数字");
        String name1 = JOptionPane.showInputDialog(null, "请输入另外一个数字");
        System.out.println(Integer.parseInt(name) + Integer.parseInt(name1));
    }
}