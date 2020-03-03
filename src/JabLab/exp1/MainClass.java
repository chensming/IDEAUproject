package JabLab.exp1;

public class MainClass {
    public static void main(String args[]) {
        ComputerFrame frame;
        frame = new ComputerFrame("算术测试");//【代码9】创建窗口，其标题为：算术测试
    }
}

/*
编写一个算书测试小软件，用来训练小学生的算术能力。程序由3个类组成，
其中Teacher类对象负责给出算术题目，并判断回答者的答案是否正确；
ComputerFrame类对象负责为算术题目提供视图，
比如用户可以通过ComputerFrame类对象提供的GUI界面看到题目，
并通过该GUI界面给出题目的答案；MainClass是软件的主类。
*/