package homework.week4;

class GeometricObject{
    public double getArea(){
        return 0.0;
    }
}


class Circle extends GeometricObject{
    private double radius;
    private double area;
    final double PI = 3.1415926;

    public Circle(double radius){
        if(radius <= 0) radius = 1.0;
        this.radius = radius;
        area = PI * radius * radius;
    }

    public double getRadius(){
        return radius;
    }

    public double getArea(){
        return area;
    }

    public void setRadius(double newRadius){
        if(newRadius <= 0) return ;
        this.radius = newRadius;
        area = PI * newRadius *newRadius;
    }
}

class Rect extends GeometricObject{
    private double width;
    private double height;
    private double area;    

    public Rect(double width,double height){
        setWidthAndHeight(width,height);
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public double getArea(){
        return area;
    }

    public void setWidth(double width){
        this.width = width;
        area = width * height;
    }

    public void setHeight(double height){
        this.height = height;
        area = width * height;
    }

    public void setWidthAndHeight(double width,double height){
        this.width = width;
        this.height = height;
        area = width * height;
    }
}

public class SecondHomeWork{  
    public static double sumArea(GeometricObject[] a){
        double sum = 0.0;
        for(int i = 0; i < a.length; i++){
            sum = sum + a[i].getArea();
        }
        return sum;
    }

    public static void main(String args[]){
        GeometricObject[] ag = new GeometricObject[4];
        ag[0] = new Circle(5.2);
        ag[1] = new Circle(6.5);
        ag[2] = new Rect(5.1,4.5);
        ag[3] = new Rect(6.5,7.1);
        System.out.println("Total areas: " + sumArea(ag));

    }
}