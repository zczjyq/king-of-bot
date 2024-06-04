package 实验4;

interface WeatherState {   //接口
    public void showState();
}

class Weather {
    WeatherState  state;
    public void show() {
        state.showState();
    }
    public void setState(WeatherState s) {
        state = s;
    }
}

public class WeatherForecast {  //主类
    public static void main(String args[]) {
        Weather weatherBeijing =new Weather();
        System.out.print("\n今天白天:");
        weatherBeijing.setState(new CloudyDayState());
        weatherBeijing.show();
        System.out.print("\n今天夜间:");
        weatherBeijing.setState(new LightRainState());
        weatherBeijing.show();
        System.out.print("转:");
        weatherBeijing.setState(new HeavyRainState());
        weatherBeijing.show();
        System.out.print("\n明天白天:");
        weatherBeijing.setState(new LightRainState());
        weatherBeijing.show();
        System.out.print("\n明天夜间:");
        weatherBeijing.setState(new CloudyLittleState());
        weatherBeijing.show();
    }
}
class CloudyLittleState implements WeatherState {
    public void showState() {
        System.out.print("少云,有时晴.");
    }
}

class CloudyDayState implements WeatherState {
    @Override
    public void showState() {
        System.out.print("少云.");
    }
    //重写public void showState()
}

class HeavyRainState implements WeatherState{
    @Override
    public void showState() {
        System.out.print("多云.");
    }//重写public void showState()
}

class LightRainState implements WeatherState {
    @Override
    public void showState() {
        System.out.print("小雨.");
    } //重写public void showState()方法
}