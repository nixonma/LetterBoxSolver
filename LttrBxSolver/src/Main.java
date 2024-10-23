import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static int lengthRec;
    private static Board board;
    private static Set<String> validWordSet;
    private static Map<Character, Set<String>> startsWithMap;
    private static int num3WordSolutions = 0;
    private static int num2WordSolutions = 0;
    private static int num4WordSolutions = 0;
    private static long loopItt = 0;
    public static void main(String args[]){
        //the letters on each side of the puzzle in groups of 3, space separated.
        //ex: abc def ghi jkl
        String input = "ipw unt sea srd";
        String[] sides = input.split(" ");

        //the number of words the puzzle suggests to solve within
        lengthRec = 2;

        //create a representation of the board for this puzzle
        board = new Board(sides);

        //read in a set of possible words to use
        ArrayList<String> wordSet = new ArrayList<>();
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
        validWordSet = new HashSet<>();
        startsWithMap = new HashMap<>();
        for(String word : wordSet){
            if(board.isValidWord(word)) {
                validWordSet.add(word);
                Character c = word.charAt(0);
                if(startsWithMap.containsKey(c)){
                    Set<String> charSet = startsWithMap.get(c);
                    charSet.add(word);
                } else {
                    HashSet<String> temp = new HashSet<>();
                    temp.add(word);
                    startsWithMap.put(c, temp);
                }
            }
        }
        System.out.println("Condensed to " + validWordSet.size() + " words");

        //print number of valid words starting with each letter of the puzzle
        for(Character c : startsWithMap.keySet()){
            System.out.println(startsWithMap.get(c).size() + " words beginning with " + c);
        }

        //solve and print stats
        ArrayList<String> solution = new ArrayList<>();
        solveSetOrder("", solution);
        System.out.println("looped " + loopItt + " times");
        System.out.println("found " + num2WordSolutions + " 2-word solutions");
        System.out.println("found " + num3WordSolutions + " 3-word solutions");
        System.out.println("found " + num4WordSolutions + " 4-word solutions");
    }

    /*
    extracted for the purpose of having different solve algorithms
     */
    public static void solveSetOrder(String current, ArrayList<String> solution) {
        if(board.usedAllLetters(solution)){
            if(solution.size() == 2){
                num2WordSolutions++;
            }else if(solution.size() == 3){
                num3WordSolutions++;
            }else {
                num4WordSolutions++;
            }
//            System.out.println("Solution: " + solution.toString() + " Yay! ");
            return;
        }
        if(solution.size() > lengthRec - 1){
            return;
        }
        if(current.length() > 0){
            Character c = current.charAt(current.length() - 1);
            for(String s : startsWithMap.get(c)){
                loopItt++;
                solution.add(s);
                solveSetOrder(s, solution);
                solution.remove(solution.size() - 1);
            }
            return;
        }
        for(String s : validWordSet){
            loopItt++;
//            if(current.equals("") || current.charAt(current.length() - 1) == s.charAt(0)){
                solution.add(s);
                solveSetOrder(s, solution);
                solution.remove(solution.size() - 1);
//            }
        }
    }
}