package 实验三;

public class StudentTest {
    public static void main(String[] args) {
        Student student1 = new Student("9220416105", "A22计算机1", "蔡嘉麒",
                "男", 21);
        Student student2 = new Student("02", "01", "张三",
                "男", 19);
        System.out.println(student1);
        System.out.println(student2);
        student2.setName("李四");
        student2.setAge(22);
        System.out.println("-----------修改后-----------");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println("年龄较大是");
        if (student1.getAge() > student2.getAge()) {
            System.out.println(student1);
        } else {
            System.out.println(student2);
        }
    }
}

