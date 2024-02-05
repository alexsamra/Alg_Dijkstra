import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import java.lang.Math;
import java.nio.file.Path;

public class hashFile {
    public static void main(String[] args) {
        int[] ht = new int[293];
        
        String filename = "/Users/alex/Desktop/CS/Algorithms/EdgarAllanPoeBellsB2022groomed.txt";
        try{
            LinkedList<String> words = getWords(filename);
            for(int i = 0; i < words.size(); i++){
                int check = doHash(words.get(i));
                int tempCheck = check;
                if(listCheck(i, words)){
                    while(ht[tempCheck] != 0){
                        if(tempCheck == 292){
                            tempCheck = 0;
                        }
                        else{
                            tempCheck++;
                        }
                    }
                    ht[tempCheck] = check;
                    String j = String.format("%03d", tempCheck);
                    String space = "";
                    for(int k = words.get(i).length(); k < 15; k++){
                        space = space.concat(" ");
                    }
                    System.out.println("Hash Address: " + j + " Hashed Word: " + words.get(i) + space + " Value of Hashed Word: " + check);
                }
            } 

            questions(ht, words);

        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static LinkedList<String> getWords(String filename) throws IOException {

        LinkedList<String> words = new LinkedList<>();
        try {
            Scanner fileReader = new Scanner(Path.of(filename));
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if(!line.equals("")) {
                    String[] lineWords = line.split("[� ]");
                    for(String word : lineWords) {
                        String word_edited = "";
                        for (int i = 0; i < word.length(); i++) {
                            char character = word.charAt(i);
                            if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character == '-' || character == '\'') {
                                word_edited += character;
                            }
                        }
                        words.add(word_edited);
                    }
                }
            }
            fileReader.close();
        } catch(FileNotFoundException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return words;
    }

    public static int doHash(String word){
    int h = 0;
    int C = 123;
    int m = 293;
    int length;
    if(word == null){
        length = 0;
    }
    else{
        length = word.length();
    }
    for(int i = 0; i < length; i++){
        h = (h * C + (int)word.charAt(i)) % m;
    }
    return h;
  }

    public static boolean listCheck(int i, LinkedList<String> words){
        for(int j = 0; j < i; j++){
            if(words.get(i).equals(words.get(j))){
                return false;
            }
        }
        return true;
    }

    public static void questions(int[] ht, LinkedList<String> words){ 
        System.out.println();
        System.out.println("Questions");
        System.out.println();

        int count = 0;

        for(int i = 0; i < ht.length; i++){
            if(ht[i] != 0){
                count++;
            }
        }

        System.out.println("How many non-empty addresses are there in the table?");
        System.out.println(count);

        int maxCount = 0;
        int tempCount = 0;
        int maxIndex = 0;
        int tempIndex = 0;
        int wrapFront = 0;
        int wrapBack = 0;

        for(int i = 0; i < ht.length; i++){
            if(ht[i] == 0){
                if(tempIndex == 0){
                    tempIndex = i;
                }
                tempCount++;
                if(tempCount > maxCount){
                    maxCount = tempCount;
                    maxIndex = tempIndex;
                }
            }
            else{
                tempCount = 0;
                tempIndex = 0;
            }
            if(i == 0){
                int j = 0;
                while(ht[j] == 0){
                    wrapFront++;
                    j++;
                }
            }
            if(i == 292){
                wrapBack = tempCount;
            }
        }
        
        if((wrapBack + wrapFront) > maxCount){
            System.out.println("What is the longest empty area in the table?");
            System.out.println((wrapBack + wrapFront));
            System.out.println("Where is it?");
            System.out.println("It wraps around");
        }
        else{
            System.out.println("What is the longest empty area in the table?");
            System.out.println(maxCount);
            System.out.println("Where is it?");
            System.out.println(maxIndex);
        }

        maxCount = 0;
        tempCount = 0;
        maxIndex = 0;
        tempIndex = 0;
        wrapFront = 0;
        wrapBack = 0;

        for(int i = 0; i < ht.length; i++){
            if(ht[i] != 0){
                if(tempIndex == 0){
                    tempIndex = i;
                }
                tempCount++;
                if(tempCount > maxCount){
                    maxCount = tempCount;
                    maxIndex = tempIndex;
                }
            }
            else{
                tempCount = 0;
                tempIndex = 0;
            }
            if(i == 0){
                int j = 0;
                while(ht[j] != 0){
                    wrapFront++;
                    j++;
                }
            }
            if(i == 292){
                wrapBack = tempCount;
            }
        }
        
        if((wrapBack + wrapFront) > maxCount){
            System.out.println("What is the longest cluster area in the table?");
            System.out.println((wrapBack + wrapFront));
            System.out.println("Where is it?");
            System.out.println("It wraps around");
        }
        else{
            System.out.println("What is the longest cluster area in the table?");
            System.out.println(maxCount);
            System.out.println("Where is it?");
            System.out.println(maxIndex);
        }
        
        int tempWords = 0;
        int maxWords = 0;
        int val = 0;
        int maxVal = 0;
        for(int i = 0; i < ht.length; i++){
            if(ht[i] != 0){
                val = ht[i];
                tempWords = 0;
                for(int j = 0; j < ht.length; j++){
                    if(val == ht[j]){
                        tempWords++;
                    }
                }
                if(tempWords > maxWords){
                    maxWords = tempWords;
                    maxVal = val;
                }
            }
        }

        System.out.println("What hash value results from the greatest number of distinct words?");
        System.out.println(maxVal);
        System.out.println("How many words have that hash value?");
        System.out.println(maxWords);

        int dif = 0;
        int difIndex = 0;
        for(int i = 0; i < ht.length; i++){
            if(ht[i] != 0){
                int tempDif = Math.abs(ht[i] - i);
                if(tempDif > dif){
                    dif = tempDif;
                    difIndex = i;
                }
            }
        }

        System.out.println("What word is placed in the table farthest from its actual hash value?");
        System.out.println(words.get(difIndex));
        System.out.println("How far away is it from its actual hash value?");
        System.out.println(dif);
         
    }

}