import javax.swing.*;

public class Main {
    public static void main(String args[]){
        String result = JOptionPane.showInputDialog("input the 12 letters clockwise from top left, space separated");
        String[] letters = new String[12];
        try{
            letters = result.split(" ");
        } catch(Error error){
            System.err.println("Yo dawg, we read the input rules?");
            System.exit(0);
        }

        result = JOptionPane.showInputDialog("enter the number of words the puzzle suggests to solve within");
        int lengthRec = Integer.parseInt(result);
        for(String a : letters){
            System.out.println(a);
        }

        //create a representation of the board for this puzzle
        Board b = new Board();

        //read in a list of possible words to use
        //clean unusable words from list

        //sort words into lists based on starting letter

        //try and create lists of words less than recommended length
    }
}
