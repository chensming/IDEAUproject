package JabLab.exp3;

public class CalendarMainClass {
    public static void main(String args[]) {
        CalendarFrame frame = new CalendarFrame();
        frame.setBounds(100, 100, 360, 300);
        frame.setVisible(true);
        frame.validate();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
                                    public void windowClosing(java.awt.event.WindowEvent e) {
                                        System.exit(0);
                                    }
                                }
        );
    }
}


/*编写一个应用程序，有一个窗口，该窗口为BorderLayout布局。
窗口的中心添加一个Panel容器：pCenter，pCenter的布局是7行7列的GriderLayout布局，
pCenter中放置49个标签，用来显示日历。窗口的北面添加一个Panel容器pNorth，其布局是FlowLayout布局，
pNorth放置两个按钮：nextMonth和previousMonth，单击nextMonth按钮，可以显示当前月的下一月的日历；
单击previousMonth按钮，可以显示当前月的上一月的日历。窗口的南面添加一个Panel容器pSouth，
其布局是FlowLayout布局，pSouth中放置一个标签用来显示一些信息。
 */