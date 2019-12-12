package homework.week6;

import java.util.Scanner;
import java.lang.Math;

abstract class GeometricObject{
    protected String color;
    protected Boolean IsFill;//是否填充
};

class Triangle extends GeometricObject{
    Scanner in = new Scanner(System.in);
    private double a,b,c;
    Triangle(){
        System.out.println("请输入三角形的边长");
        a = in.nextDouble();
        b = in.nextDouble();
        c = in.nextDouble();
        while(a + b < c || a + c < b || b + c < a)
        {
            System.out.println("三边不构成三角形");
            System.out.println("请重新输入三角形的边长");
            // System.exit(1); 意外退出程序方法 若参数为0代表正常退出
            a = in.nextDouble();
            b = in.nextDouble();
            c = in.nextDouble();
        }  
        System.out.println("请输入三角形的颜色,如blue或蓝色"); 
        color = in.next();
        System.out.println("请输入是否填充,true/false");
        String str = in.next();
        IsFill = Boolean.parseBoolean(str);
    }

    public void showSide(){
        System.out.println("三边分别是 " + a + " " + b + " " + c );
    }

    public void showColor(){
        System.out.println("颜色是 " + color);
    }

    public void showIsFill(){
        System.out.println("填充？" + IsFill);
    }

    public void showPerimeter(){
        System.out.println("周长: " + (a + b + c));
    }

    public void showArea(){
        double s = (a + b + c) / 2;
        System.out.println("面积: " + Math.sqrt(s * (s - a) * (s - b) * (s - c)));
    }

    

};

public class Main{
    public static void main(String args[]){
        Triangle a = new Triangle();
        a.showSide();
        a.showPerimeter();
        a.showArea();
        a.showColor();
        a.showIsFill();
    }
}
