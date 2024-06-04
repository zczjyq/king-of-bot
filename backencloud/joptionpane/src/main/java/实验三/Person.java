package 实验三;

public class Person {
    private Address address;
    private String name;
    private String IdCard;

    public Person(Address address, String name, String idCard) {
        this.address = address;
        this.name = name;
        IdCard = idCard;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    @Override
    public String
    toString() {
        return "地址:" + address.getPrivince() + " " + address.getCity() +
                ", 名字:" + name  +
                ", 身份证:" + IdCard ;
    }
}
