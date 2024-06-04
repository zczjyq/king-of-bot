package 实验4;

interface StudentManageInterface{
    public void setFee(int fee);
    public int getFee();
}

interface TeacherManageInterface{
    public void setPay(int pay);
    public int getPay();
}

class Graduate implements StudentManageInterface, TeacherManageInterface {
    String name, sex;
    int fee, pay;

    public Graduate(String name, String sex, int fee, int pay) {
        this.name = name;
        this.sex = sex;
        setFee(fee);
        setPay(pay);
    }

    @Override
    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public int getFee() {
        return fee;
    }

    @Override
    public void setPay(int pay) {
        this.pay = pay;
    }

    @Override
    public int getPay() {
        return pay;
    }
}

public class MainClass2 {
    public static void main(String[] args) {
        Graduate graduate = new Graduate("zhangsan", "女", 90000, 80000);
        if (graduate.getFee() - graduate.getPay() < 20000) System.out.println("you need a loan!");
    }
}
