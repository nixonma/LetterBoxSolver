import javax.swing.*;

public class Main {
    public static void main(String args[]){
        String result = JOptionPane.showInputDialog("input the 12 letters clockwise from top left, space separated");
        String[] input = new String[12];
        try{
            input = result.split(" ");
        } catch(Error error){
            System.err.println("Yo dawg, we read the input rules?");
            System.exit(0);
        }
        for(String a : input){
            System.out.println(a);
        }
        Board b = new Board();
    }
}
