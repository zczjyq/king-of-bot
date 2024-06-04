package 实验三;

public class Student {
    private String StudentNumber;
    private String classNumber;
    private String name;
    private String gender;
    private int age;
    private static int count;

    {
        count ++;
    }

    public Student(String studentNumber, String classNumber, String name, String gender, int age) {
        StudentNumber = studentNumber;
        this.classNumber = classNumber;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "学生[" +
                "学号=" + StudentNumber +
                ", 班级=" + classNumber +
                ", 名字=" + name +
                ", 性别=" + gender +
                ", 年龄=" + age +
                ']';
    }

    public int getCount() {
        return count;
    }
}
