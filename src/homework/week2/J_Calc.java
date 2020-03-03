package homework.week2;

import java.util.Scanner;

public class J_Calc{
    public static void main(String args[]){
        double result = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("请输入一个数字 (空格 [%]),回车或空格在输入运算符");
        double numA = in.nextDouble() * 1000;
        char operaA = in.next().charAt(0);

        /*
            flag为0代表+
            flag为1代表-
            flag为2代表*
            flag为3代表/

        */
        int flag = -1;
        switch(operaA){
            case '+':
                flag = 0 ;
                break;
            case '-':
                flag = 1;
                break;
            case '*':
                flag = 2;
                break;
            case '/':
                flag = 3;
                break;
            case '%':
                numA =  numA/100;
                System.out.println("请输入一个运算符");
                char operaB = in.next().charAt(0);
                switch(operaB){
                    case '+':
                        flag = 0 ;
                        break;
                    case '-':
                        flag = 1;
                        break;
                    case '*':
                        flag = 2;
                        break;
                    case '/':
                        flag = 3;
                        break;
                }
                break;
            default:
                System.out.println("输入有误");
        }
        // System.out.println("flag: "+flag);

        System.out.println("请输入第二个数");
        double numB = in.nextDouble() * 1000;

        //检查是否还有%
        if ( in.hasNext("%") )
            numB = numB / 100;
        switch(flag){
            case 0:
                result = (numA + numB) / 1000;
                break;
            case 1:
                result = (numA - numB) / 1000;
                break;
            case 2:
                result = (numA * numB) / 1000;
                break;
            case 3:
                result = (numA / numB) /1000;
                break;
            default:
                System.out.println("*** error! ***");
        }

        System.out.println("result: "+result);
    }
}