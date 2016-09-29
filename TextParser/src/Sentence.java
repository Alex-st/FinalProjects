import java.util.ArrayList;
import java.util.List;

/**
 * Class for whole sentence as list of Textable elements
 *
 * Created by alex on 5/20/15.
 */

public class Sentence {
    private List<Textable> sentence;

    public Sentence() {
        sentence = new ArrayList<Textable>();
    }

    public Sentence(Iterable<Textable> w) {

        sentence = new ArrayList<Textable>();

        for (Textable i : w) {
            sentence.add(i);
        }
    }

    public void addWord(Word word) {
        sentence.add(word);
    }

    public List<Textable> getSentence() {
        return sentence;
    }

    public String toString() {

        String tmp = "";
        for (Textable i: sentence) {
            tmp+=i.toString();
        }

        return tmp;
    }
}