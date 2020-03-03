package JabLab.exp2;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class SignalFrame extends Frame implements ItemListener {
    Choice choice;
    SignalCanvas signal = null;
    String itemRed = "红灯", itemYellow = "黄灯", itemGreen = "绿灯";

    public SignalFrame() {
        choice = new Choice();  //【代码1】创建choice
        choice.add(itemRed);    //【代码2】创建choice添加itemRed
        choice.add(itemYellow); //【代码3】创建choice添加itemYellow
        choice.add(itemGreen);  //【代码4】创建choice添加itemGreen
        choice.addItemListener(this);  //将当前窗口注册为choice的ItemEvent事件监视器
        add(choice, BorderLayout.NORTH);
        signal = new SignalCanvas();
//        try {
//            Class cs = Class.forName("SignalCanvas");
//            signal = (SignalCanvas) cs.newInstance();
        add(signal, BorderLayout.CENTER);
//        } catch (Exception e) {
//        add(new Label("您还没有编写SignalCanvas类"), BorderLayout.CENTER);
//        }
        setBounds(100, 100, 360, 300);
        setVisible(true);
        validate();
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  System.exit(0);
                              }
                          }
        );
    }

    public void itemStateChanged(ItemEvent e) {
        String item = choice.getSelectedItem();  // choice返回被选中的条目
        int w = signal.getBounds().width;
        int h = signal.getBounds().height;
        int m = Math.min(w, h);
        signal.setRadius(m / 6);
        if (item.equals(itemRed)) {
            if (signal != null) {
                signal.setRed(1);
                signal.setYellow(0);
                signal.setGreen(0);
                signal.setPosition(w / 3, 0);
                signal.repaint();
            }
        } else if (item.equals(itemYellow)) {
            if (signal != null) {
                signal.setRed(0);
                signal.setYellow(1);
                signal.setGreen(0);
                signal.setPosition(w / 3, h / 3);
                signal.repaint();
            }
        } else if (item.equals(itemGreen)) {
            if (signal != null) {
                signal.setRed(0);
                signal.setYellow(0);
                signal.setGreen(1);
                signal.setPosition(w / 3, 2 * h / 3);
                signal.repaint();
            }
        }
    }
}