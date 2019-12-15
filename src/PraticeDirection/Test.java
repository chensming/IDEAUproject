package PraticeDirection;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args){
        File file;
        BufferedReader br = null;
        try{
            //read the words
            file = new File("./src/Files/a.txt");
            br = new BufferedReader(new FileReader(file));
            ArrayList<String> words = new ArrayList<>();
            String strLine;
            while((strLine = br.readLine()) != null){
                String[] wordsInThisLine =strLine.split(" ");
                words.addAll(Arrays.asList(wordsInThisLine));
            }

            words.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            //print the words
            for (String word : words) System.out.println(word);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(br != null){
                    br.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
