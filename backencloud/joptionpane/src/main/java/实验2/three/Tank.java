package 实验2.three;

public class Tank {
    private double speed;
    private int bulletAmount;

    public Tank(int bulletAmount) {
        this.speed = 0;
        this.bulletAmount = bulletAmount;
    }

    public void speedUp(double up) {
        this.speed += up;
    }

    public void speedDown(double down) {
        if (this.speed - down >= 0)
            this.speed -= down;
        else System.out.println("无法减速");
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getBulletAmount() {
        return bulletAmount;
    }

    public void setBulletAmount(int bulletAmount) {
        this.bulletAmount = bulletAmount;
    }

    public void fire() {
        if (this.bulletAmount >= 1) {
            this.bulletAmount -= 1;
            System.out.println("打出一发炮弹");
        } else {
            System.out.println("没有炮弹了");
        }
    }

    @Override
    public String toString() {
        return "速度是" + speed +
                ", 还有炮弹=" + bulletAmount +
                '颗';
    }
}
