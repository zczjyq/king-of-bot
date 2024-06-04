package 实验4;

class School {
    String schoolName;
    //内部类声明对象newsPaper
    InnerNewsPaper newsPaper;
    School() {
        this("某某大学"); //调用带参数的构造方法
    }
    School(String s) {
       //创建对象newsPaper
        this.newsPaper = new InnerNewsPaper();
        String [] content = {"学校举办迎新会.", "机械系获得机器人大赛冠军.",
                "计算机学院召开学生会换届大会."};
        schoolName = s;
        newsPaper.setContent(content);
    }
    public void showNews(){
        newsPaper.showContent();
    }
    class InnerNewsPaper {
        String [] content;
        String paperName = "校新闻周报";
        void setContent(String []s){
            content = s;
        }
        public void showContent(){
            System.out.println(schoolName);
            for(int i=0;i<content.length;i++){
                System.out.println(content[i]);
            }
        }
    }
}

public class MainClass1 {
    public static void main(String args[]) {
        School school = new School("创新大学");
        school.showNews();
    }
}