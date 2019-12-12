package homework.week2;

import java.util.Random;

public class J_Test{
    public static void main(String args[]){
        String[] size = {"Ace","2","3","4","5","6","7","8","9","10",
        "Jack","Queen","King"};
        String[] color = {"Clubs","Diamonds","Hearts","Spades"};
        Random rand = new Random();
        
        int randNumber;
        randNumber = rand.nextInt(53)+1;
        String cardSize = size[randNumber%13];
        String cardColor = color[randNumber%4];
        System.out.println("The card you picked is "+cardSize+" of "+cardColor);
    }
}

