package 实验三;

public class Address {
    private String privince;
    private String city;

    public Address(String privince, String city) {
        this.privince = privince;
        this.city = city;
    }

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
