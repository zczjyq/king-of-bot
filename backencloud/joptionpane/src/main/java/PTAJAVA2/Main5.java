package PTAJAVA2;

import java.text.DecimalFormat;
import java.util.*;

abstract class Person {
    public String name;
    public int age;
    public boolean gender;

    public Person(String name, int age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person:" + name + "-" + age + "-" + gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && gender == person.gender && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }

}

class Student extends Person {
    private String stuNo;
    private String clazz;

    public Student(String name, int age, boolean gender, String stuNo, String clazz) {
        super(name, age, gender);
        this.stuNo = stuNo;
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(stuNo, student.stuNo) && Objects.equals(clazz, student.clazz);
    }

    @Override
    public String toString() {
        return "Student:" + super.name + '-' + super.age + super.gender + '-' + "-" + stuNo + '-' + clazz;
    }
}

class Company {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name);
    }
}

class Employee extends Person {
    private Company company;
    private double salary;

    public Employee(String name, int age, boolean gender, Company company, double salary) {
        super(name, age, gender);
        this.company = company;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee:" + super.name + '-' + super.age + '-' + super.gender + '-' + company + "-" + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        DecimalFormat df = new DecimalFormat("#.#");
        Double s1 = Double.valueOf(df.format(employee.salary));
        Double s2 = Double.valueOf(df.format(salary));
        return Double.compare(s1, s2) == 0 && Objects.equals(company, employee.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company, salary);
    }
}

public class Main5 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        List<Person> personList = new ArrayList<>();
        while (true) {
            String op = cin.next();
            if (op.equals("s")) {
                String name = cin.next();
                int age = cin.nextInt();
                boolean gender = cin.nextBoolean();
                String stuNo = cin.next(), clazz = cin.next();
                Student student = new Student(name, age, gender, stuNo, clazz);
                personList.add(student);
            } else if (op.equals("e")) {
                String name = cin.next();
                int age = cin.nextInt();
                boolean gender = cin.nextBoolean();
                double salary = cin.nextDouble();
                String comname = cin.next();
                Company company = new Company(comname);
                Employee employee = new Employee(name, age, gender, company, salary);
                personList.add(employee);
            } else {
                break;
            }
        }
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.name.equals(o2.name)) return o1.age - o2.age;
                else return o1.name.compareTo(o2.name);
            }
        });

        for (Person p : personList) {
            System.out.println(p.toString());
        }

        List<Student> stuList = new ArrayList<>();
        List<Employee> empList = new ArrayList<>();
        for (Person p : personList) {
            if (p instanceof Student) {
                boolean flag = true;
                for (Student s : stuList) {
                    if (s.equals(p)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) stuList.add((Student)p);
            } else {
                boolean flag = true;
                for (Employee e : empList) {
                    if (e.equals(p)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) empList.add((Employee)p);
            }
        }

        System.out.println("stuList");
        for (Student s : stuList) {
            System.out.println(s.toString());
        }

        System.out.println("empList");
        for (Employee e : empList) {
            System.out.println(e.toString());
        }
    }
}
