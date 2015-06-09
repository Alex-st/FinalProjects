import java.util.ArrayList;

/**
 * Class for words itself (which contains only letters).
 * Сontains symbols as List
 * Created by alex on 5/20/15.
 */

public class Word extends Textable{
    private ArrayList<Symbol> word;

    public Word(){
        word = new ArrayList<Symbol>();
    }

    public Word(Iterable<Symbol> s) {

//        word = new ArrayList<Element>(Arrays.asList(array))

        word = new ArrayList<Symbol>();
        for (Symbol i : s) {
            word.add(i);
        }
    }
    public Word(Symbol s) {
        word = new ArrayList<Symbol>();
        word.add(s);
    }

    public int size() {
        return word.size();
    }

    public void addSymbol(Symbol symbol) {
        word.add(symbol);
    }

    public String toString() {
        String tmp = "";
        for (Symbol i: word) {
            tmp+=i.getSymbol();
        }
        return tmp;
    }

    /**
     * Method "equals" is required for chosen task of
     * search unique word in first sentence
     * @param o here should be other word to check
     * @return boolean Returns result of comparing.
     */

    public boolean equals(Object o) {
        if (!(o instanceof Word)) {
            return false;
        }
        Word temp = (Word)o;

        if (this.word.size()!=temp.word.size())
            return false;

        for (int i = 0; i < word.size(); i++) {
            if (!word.get(i).equals(temp.word.get(i)))
                return false;
        }

        return true;
    }

}

