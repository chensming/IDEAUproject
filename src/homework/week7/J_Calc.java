package homework.week7;

import java.util.Scanner;

public class J_Calc{
    public static void main(String args[]){
        try{
            Calculate();
        } catch(ArithmeticException e){
            System.out.println("the divisor can't be zero");
        }
    }

    public static void Calculate() throws ArithmeticException{
        double result = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("������һ������ (�ո� [%]),�س���ո������������");
        double numA = in.nextDouble() * 1000;
        char operaA = in.next().charAt(0);
        
        /*
            flagΪ0����+
            flagΪ1����-
            flagΪ2����*
            flagΪ3����/
        
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
                System.out.println("������һ�������");
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
                System.out.println("��������");
        }
        // System.out.println("flag: "+flag);

        System.out.println("������ڶ�����");
        double numB = in.nextDouble() * 1000;

        if(numB < 0.0000001 && numB > -0.0000001 && flag == 3){
            throw new ArithmeticException("zero");
        }


        //����Ƿ���%
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