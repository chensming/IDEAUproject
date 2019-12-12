package homework;
import java.util.ArrayList;

public class Pattern{
    public static <E extends Comparable<E>> E max(ArrayList<E>list) {
        E max = list.get(0);
        E temp;
        for(int i = 1; i < list.size(); i++){
            temp = list.get(i);
            if(temp.compareTo(max) > 0)
                max = temp;  //temp > max
        }
        return max;
    }

    public static void main(String[] args) {
        Pattern a = new Pattern();
        ArrayList<Double> numbers = new ArrayList<>();
        numbers.add(Math.random() * 100);
        numbers.add(50.6);
        numbers.add(60.4);
        numbers.add(30.7);
        numbers.add(88.6);
        System.out.println("max: " + a.max(numbers));
    }
}