// ////////////////////////////////////////////////////////
//
// J_AWT.java
//
// 开发者: 雍俊海
// ////////////////////////////////////////////////////////
// 简介:
//     AWT图形用户界面例程。
// ////////////////////////////////////////////////////////
//     雍俊海. Java程序设计教程(第3版). 北京: 清华大学出版社. 2014.

package swing;

public class J_AWT {
    public static void main(String args[]) {
        java.awt.Frame app = new java.awt.Frame();
        app.setTitle("AWT框架");
        app.add(new java.awt.Button("AWT按钮"),
                java.awt.BorderLayout.CENTER);
        app.addWindowListener(new java.awt.event.WindowAdapter() {
                                  public void windowClosing(java.awt.event.WindowEvent e) {
                                      System.exit(0);
                                  } // 方法windowClosing结束
                              } // 实现抽象类WindowAdapter的内部子类结束
        ); // addWindowListener方法调用结束
        app.setSize(250, 100);
        app.setVisible(true);
    } // 方法main结束
} // 类J_AWT结束
