package 实验三;

public class Main2 {
    public static void main(String[] args) {
        Address address = new Address("浙江", "宁波");
        Person person = new Person(address, "蔡嘉麒", "1234352343228479828");
        System.out.println(person);
    }
}
