import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static int lengthRec;
    private static Board board;
    private static Set<String> validWordSet;
    private static int numSolutions = 0;
    public static void main(String args[]){
        String result = JOptionPane.showInputDialog("input the 12 letters clockwise from top left in sets of 3, space separated. \n (ex: abc def ghi jkl)");
        String[] sides = new String[4];
        try{
            sides = result.split(" ");
            if(sides.length != 4)
                throw new ArrayIndexOutOfBoundsException("incorrect number of letter groups entered");
        } catch(Error error){
            System.err.println("Yo dawg, we read the input rules?");
            System.exit(0);
        }

        result = JOptionPane.showInputDialog("enter the number of words the puzzle suggests to solve within");
        lengthRec = Integer.parseInt(result);

        //create a representation of the board for this puzzle
        board = new Board(sides);

        //read in a set of possible words to use
        ArrayList<String> wordSet = new ArrayList<>();
        //read list of words in
        try {
            File dict = new File("words_cleaned.txt");
            Scanner fileReader = new Scanner(dict);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                wordSet.add(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Starting with " + wordSet.size() + " words");
        //clean unusable words from list
        validWordSet = new HashSet<String>();
        for(String word : wordSet){
            if(board.isValidWord(word))
                validWordSet.add(word);
        }
        System.out.println("Condensed to " + validWordSet.size() + " words");

        ArrayList<String> solution = new ArrayList<>();
        solveSetOrder("", solution);
        System.out.println("found " + numSolutions + " solutions");
    }

    /*
    extracted for the purpose of having different solve algorithms
     */
    public static void solveSetOrder(String current, ArrayList<String> solution) {
        if(board.usedAllLetters(solution)){
            numSolutions++;
            System.out.println("Solution: " + solution.toString() + " Yay! ");
            return;
        }
        if(solution.size() > lengthRec - 1){
            return;
        }
        for(String s : validWordSet){
            if(current.equals("") || current.charAt(current.length() - 1) == s.charAt(0)){
                solution.add(s);
                solveSetOrder(s, solution);
                solution.remove(solution.size() - 1);
            }
        }
    }
}