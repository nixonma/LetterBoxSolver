import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordListCleaner {
    public static void main(String[] args){
        ArrayList<String> wordList = new ArrayList<>();
        //read list of words in
        try {
            //words.txt gets periodically updated when I try a word on the list and NYT
            //does not take the word.
            //Most recent frustration: "biconditional"
            File myObj = new File("words.txt");
            Scanner fileReader = new Scanner(myObj);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                wordList.add(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //clean out any words that aren't possible/allowed in letter boxed
        StringBuilder output = new StringBuilder();
        for(String s : wordList){
            if(s.length() > 2){
                boolean noRepeatLetters = true;
                for(int i = 1; i < s.length(); i++){
                    if(s.charAt(i) == s.charAt(i - 1)){
                        noRepeatLetters = false;
                    }
                }
                if(noRepeatLetters){
                    output.append(s);
                    output.append("\n");
                }
            }
        }
        System.out.println(output.toString());

        //write cleaned words to file
        //currently commented out to prevent overwrites
//        try {
//            FileWriter myWriter = new FileWriter("words_cleaned.txt");
//            myWriter.write(output.toString());
//            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
    }
}