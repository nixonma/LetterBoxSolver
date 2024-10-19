import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {
    private final String[] sides;
    private final HashSet<String> letters;
    public Board(String[] puzzleSides){
        this.letters = new HashSet<>();
        for(String s : puzzleSides){
            String[] temp = s.split("");
            this.letters.addAll(Arrays.asList(temp));
        }
        this.sides = puzzleSides;
    }

    public boolean isValidWord(String word) {
        for(int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1);
//            System.out.println("testing " + word + " position: " + i);
            if (!letters.contains(letter)){
//                System.out.println("fail");
                return false;
            }

            if(i < word.length() - 1) {
                for (String side : sides) {
                    //test for letter and following not on the same side
                    if (side.contains(letter) && side.contains(word.substring(i + 1, i + 2)))
                        return false;
                }
            }
        }
        return true;
    }

    public boolean usedAllLetters(ArrayList<String> solution) {
        Set<Character> used = new HashSet<>();
        for(String word : solution){
            for(char c : word.toCharArray()){
                used.add(c);
            }
        }
        for(String side : this.sides){
            for(char sc : side.toCharArray()){
                if(!used.contains(sc))
                    return false;
            }
        }
        return true;
    }
}
