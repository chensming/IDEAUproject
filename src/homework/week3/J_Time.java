package homework.week3;

import java.util.Date;
import java.text.*;
import java.util.Random;

class StopWatch{
    long startTime;
    long endTime;
    // long diff;

    StopWatch(){
        startTime = System.currentTimeMillis();
        System.out.println("initialize successfully!");
    }

    public long getStartTime(){
        return startTime;
    }

    public long getEndTime(){
        return endTime;
    }
    
    public void start(){
        startTime = System.currentTimeMillis();
        System.out.println("setStartTime: "+startTime);
    }

    public void stop(){
        endTime = System.currentTimeMillis();
        System.out.println("endTime:      "+endTime);
    }

    public long getElaspedTime(){
        // diff = endTime - startTime;
        return endTime - startTime; 
    }

}

public class J_Time{
    public static void main(String args[]){
        // Date d = new Date();
        StopWatch s = new StopWatch();
        Random rand = new Random();
        int[] numbers = new int[100000]; 
        for(int i = 0; i < 100000; i++){
            numbers[i] = rand.nextInt();
        }
        s.start(); //start test
        for(int i = 0; i < numbers.length; i++){
            int k = i;
            for(int j = k + 1; j < numbers.length - 1; j++){
                if(numbers[j] < numbers[k])
                    k = j;  //´æ´¢×îÐ¡Öµ
            }
            if(i != k){
                int temp = numbers[i];
                numbers[i] = numbers[k];
                numbers[k] = temp;
            }
        }
        s.stop(); //end test
        System.out.println("diffTime:     "+s.getElaspedTime());
    
    }
}