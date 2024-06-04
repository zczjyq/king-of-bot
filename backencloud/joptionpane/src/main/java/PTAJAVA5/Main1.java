//package PTAJAVA5;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
////class Person {
////    private String name;
////    private boolean gender;
////    private String birthdate;
////
////    public Person() {
////    }
////
////    public Person(String name, boolean gender) {
////        this.name = name;
////        this.gender = gender;
////    }
////
////    public Person(String name, boolean gender, String birthdate) {
////        this.name = name;
////        this.gender = gender;
////        this.birthdate = birthdate;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public boolean isGender() {
////        return gender;
////    }
////
////    public void setGender(boolean gender) {
////        this.gender = gender;
////    }
////
////    public String getBirthdate() {
////        return birthdate;
////    }
////
////    public void setBirthdate(String birthdate) {
////        this.birthdate = birthdate;
////    }
////}
////在这里给出Judgeable接口的定义，入参为String，返回boolean。
//interface Judgeable {
//    boolean judge(String s);
//    static int countPerson(List<Person> personList, Judgeable judger) {
//        return 0;
//    }
//}
//
//public class Main1 {
//    public static int countPerson(List<Person> personList, Judgeable judger) {
//        int n = 0;
//        for (Person person : personList) {
//            if (judger.judge(person.getName()))
//                n++;
//        }
//        return n;
//    }
//
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        List<Person> personList = new ArrayList<Person>();
//        String name;
//        boolean gender;
//        String  birthDate;
//
//        //在这里输入个数
//        int n = sc.nextInt();
//
//        //循环创建person对象存入personList最后
//        for (int i = 0; i < n; i ++ ) {
//            name = sc.next();
//            if (name.equals("null")) {
//                name = "";
//            }
//            gender = sc.nextBoolean();
//            birthDate = sc.next();
//            Person person = new Person(name, gender, birthDate);
//            personList.add(person);
//        }
//        //注意：当name输入为null时，将其置为空
//
//        int nameLength5 = countPerson(personList, new Judgeable() {
//            @Override
//            public boolean judge(String s) {
//                return s.length() == 5;
//            }
//            //Judgeable的实现代码
//
//        });
//
//        System.out.println("Number of person with nameLength == 5:"+nameLength5);
//        int nameLength7 = countPerson(personList, new Judgeable() {
//            @Override
//            public boolean judge(String s) {
//                return s.length() == 7;
//            }
//            //Judgeable的实现代码
//        });
//
//        System.out.println("Number of person with nameLength == 7:"+nameLength7);
//        int nameisnull = countPerson(personList, new Judgeable() {
//            @Override
//            public boolean judge(String s) {
//                return s.isEmpty();
//            }
//            //Judgeable的实现代码
//        });
//
//        System.out.println("Number of person with null name:"+nameisnull);
//    }
//}