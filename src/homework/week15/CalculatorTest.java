package homework.week15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class CalculatorTest {
    String str1 = "0";
    String str2 = "0";
    String signal = "+";
    String result = "";

    int k1 = 1; // 开关1 用于选择输入方向 将要写入str1或 str2
    int k2 = 1;// 开关2 符号键 次数 k2>1说明进行的是2+3-9+8 这样的多符号运算
    int k3 = 1;// 开关3 str1 是否可以被清0 ==1时可以 !=1时不能被清0
    int k4 = 1;// 开关4 str2 同上
    int k5 = 1;// 开关5 控制小数点可否被录入 ==1时可以 !=1 输入的小数点被丢掉
    JButton store; // 寄存器 记录 是否连续按下符号键

    Vector vt = new Vector(20, 10);

    JFrame frame = new JFrame("计算器");
    JTextField resultTextField = new JTextField(result, 20);
    JButton clearButton = new JButton("清除");
    JButton button0 = new JButton("0");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");
    JButton buttonDot = new JButton(".");
    JButton buttonAdd = new JButton("+");
    JButton buttonSub = new JButton("-");
    JButton buttonMul = new JButton("*");
    JButton buttonDiv = new JButton("/");
    JButton buttonEquel = new JButton("=");

    CalculatorTest() {
        button0.setMnemonic(KeyEvent.VK_0);//快捷键
        button1.setMnemonic(KeyEvent.VK_1);
        button2.setMnemonic(KeyEvent.VK_2);
        button3.setMnemonic(KeyEvent.VK_3);
        button4.setMnemonic(KeyEvent.VK_4);
        button5.setMnemonic(KeyEvent.VK_5);
        button6.setMnemonic(KeyEvent.VK_6);
        button7.setMnemonic(KeyEvent.VK_7);
        button8.setMnemonic(KeyEvent.VK_8);
        button9.setMnemonic(KeyEvent.VK_9);
        buttonDot.setMnemonic(KeyEvent.VK_DEAD_ABOVEDOT);
        buttonAdd.setMnemonic(KeyEvent.VK_ADD);
        buttonSub.setMnemonic(KeyEvent.VK_SUBTRACT);
        buttonMul.setMnemonic(KeyEvent.VK_MULTIPLY);
        buttonDiv.setMnemonic(KeyEvent.VK_DIVIDE);

        resultTextField.setHorizontalAlignment(JTextField.RIGHT);

        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(4, 4, 5, 5));
        pan.add(button7);
        pan.add(button8);
        pan.add(button9);
        pan.add(buttonDiv);
        pan.add(button4);
        pan.add(button5);
        pan.add(button6);
        pan.add(buttonMul);
        pan.add(button1);
        pan.add(button2);
        pan.add(button3);
        pan.add(buttonSub);
        pan.add(button0);
        pan.add(buttonDot);
        pan.add(buttonEquel);
        pan.add(buttonAdd);
        pan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel pan2 = new JPanel();
        pan2.setLayout(new BorderLayout());
        pan2.add(resultTextField, BorderLayout.WEST);
        pan2.add(clearButton, BorderLayout.EAST);

        frame.setLocation(300, 200);
        frame.setResizable(false);//调大小就变形了，不能调
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(pan2, BorderLayout.NORTH);
        frame.getContentPane().add(pan, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        //事件处理程序

        //数字键
        class Listener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String ss = ((JButton) e.getSource()).getText();
                store = (JButton) e.getSource();
                vt.add(store);
                if (k1 == 1) {
                    if (k3 == 1) {
                        str1 = "";
                        k5 = 1;// 还原开关k5状态
                    }
                    str1 = str1 + ss;

                    k3 = k3 + 1;

                    resultTextField.setText(str1);
                } else if (k1 == 2) {
                    if (k4 == 1) {
                        str2 = "";
                        k5 = 1;// 还原开关k5状态
                    }
                    str2 = str2 + ss;
                    k4 = k4 + 1;
                    resultTextField.setText(str2);
                }
            }
        }

        //符号
        class ListenerSignal implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ss2 = ((JButton) e.getSource()).getText();
                store = (JButton) e.getSource();
                vt.add(store);

                if (k2 == 1) {
                    k1 = 2;
                    k5 = 1;
                    signal = ss2;
                    k2 = k2 + 1;
                } else {
                    int a = vt.size();
                    JButton c = (JButton) vt.get(a - 2);

                    if (!(c.getText().equals("+"))
                            && !(c.getText().equals("-"))
                            && !(c.getText().equals("*"))
                            && !(c.getText().equals("/"))) {
                        cal();
                        str1 = result;
                        k1 = 2;// 开关 k1 为1时,向数1写 为2时，向数2写
                        k5 = 1;
                        k4 = 1;
                        signal = ss2;
                    }
                    k2 = k2 + 1;
                }
            }
        }

        //清除
        class ListenerClear implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                k5 = 1;
                k2 = 1;
                k1 = 1;
                k3 = 1;
                k4 = 1;
                str1 = "0";
                str2 = "0";
                signal = "";
                result = "";
                resultTextField.setText(result);
                vt.clear();
            }
        }

        class ListenerEquel implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                cal();
                k1 = 1;
                k2 = 1;
                k3 = 1;// 还原开关k3状态
                k4 = 1;// 还原开关k4状态

                str1 = result;//为7+5=12 +5=17 这种计算做准备
            }
        }


        //小数点
        class ListenerDot implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                if (k5 == 1) {
                    String ss2 = ((JButton) e.getSource()).getText();
                    if (k1 == 1) {
                        if (k3 == 1) {
                            str1 = "";
                            k5 = k1;// 还原开关k5状态
                        }
                        str1 = str1 + ss2;

                        k3 = k3 + 1;

                        resultTextField.setText(str1);

                    } else if (k1 == 2) {
                        if (k4 == 1) {
                            str2 = "";
                            k5 = 1;
                        }
                        str2 = str2 + ss2;

                        k4 = k4 + 1;

                        resultTextField.setText(result);
                    }
                }
                k5 = k5 + 1;
            }
        }

        // 注册 监听器
        ListenerEquel lsEquel = new ListenerEquel();
        Listener ls = new Listener(); // 临听数字键
        ListenerSignal lsSignal = new ListenerSignal();//符号键
        ListenerClear lsClear = new ListenerClear();//清除键
        ListenerDot lsDot = new ListenerDot();//小数点键


        button0.addActionListener(ls);
        button1.addActionListener(ls);
        button2.addActionListener(ls);
        button3.addActionListener(ls);
        button4.addActionListener(ls);
        button5.addActionListener(ls);
        button6.addActionListener(ls);
        button7.addActionListener(ls);
        button8.addActionListener(ls);
        button9.addActionListener(ls);
        buttonAdd.addActionListener(lsSignal);
        buttonSub.addActionListener(lsSignal);
        buttonMul.addActionListener(lsSignal);
        buttonDiv.addActionListener(lsSignal);
        buttonDot.addActionListener(lsDot);
        buttonEquel.addActionListener(lsEquel);
        clearButton.addActionListener(lsClear);


        // 关闭事件处理程序
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        }


        //计算
        public void cal () {
            double a2;//运算数1
            double b2;//运算数2
            String c = signal;
            double result2 = 0;

            if(c.equals("")) {
                resultTextField.setText("请输入运算符");
            } else {
                if(str1.equals("."))
                    str1 = "0.0";
                if(str2.equals("."))
                    str2 = "0.0";
                a2 = Double.valueOf(str1).doubleValue();
                b2 = Double.valueOf(str2).doubleValue();

                if(c.equals("+")) {
                    result2 =  a2 + b2;
                }
                if(c.equals("-")){
                    result2 = a2 - b2;
                }
                if(c.equals("*")) {
                    result2 = a2 * b2;
                }
                if(c.equals("/")){
                    if(b2 == 0){
                        result2 = 0;
                    }else{
                        result2 = a2 /b2;
                    }
            }
                result = ((new Double(result2)).toString());

                resultTextField.setText(result);
        }
    }

    public static void main(String[] args) {
        CalculatorTest cal = new CalculatorTest();
    }
}