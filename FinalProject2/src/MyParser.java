import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Essential project class which parses text in text file and searches for unique word in first sentence
 * Created by alex on 5/17/15.
 */
public class MyParser {

    /**
     * List of all sentences, each sentence consists of words and special symbols like " " and ",".
     */
    private List<Sentence> sentences;


    /**
     * List of all words in text (without any special sentences).
     */
    private List<Textable> words;

    /**
     * Whole initial "pure" text as list of symbols
     */
    private List<Symbol> symbols;

    public MyParser() {
        words = new ArrayList<Textable>();
        sentences = new ArrayList<Sentence>();
    }

    /**
     * Method that takes file name with text
     * and fulfill list of symbols, words and sentences.
     * Method also outputs list of all sentences and words
     * @param str String with file name for parsing text
     */
    public void getTextFromFile(String str) {

        String tmp="";

        try {
            Scanner in = new Scanner(new File(str));
            while (in.hasNextLine()) {
                tmp = tmp+" "+in.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        symbols = Symbol.toList(tmp.toCharArray());

        //Temporary queue for symbols which will be later assigned to single word
        Queue<Symbol> tmpS = new LinkedList<Symbol>();

        //Temporary queue for words, which later will be combined in single sentence
        Queue<Textable> tmpW = new LinkedList<Textable>();

        //Temporary queue for different word separators, which later should be included to sentence
        Queue<SpecSymbol> tmpSS = new LinkedList<SpecSymbol>();

        for (Symbol i:symbols) {

            // each type of separators is processed in its own way

            // finding space we create word from symbols in temporary symbol queue
            // and add it to tmpW (with separators in SpecSymbol)
            if (i.getSymbol() ==' ') {
                if (!tmpSS.isEmpty()) {
                    tmpW.add(new SpecSymbol(i));
                    tmpSS.clear();
                }

                // if there are several spaces we keep only one
                if (tmpS.isEmpty()) {
                    continue;
                }

                words.add(new Word(tmpS));
                tmpW.add(new Word(tmpS));
                tmpW.add(new SpecSymbol(i));
                tmpS.clear();
                continue;
            }

            //finding special separating symbol we add it to temporal queue
            // and all symbols from tmpS add to new word
            if (i.getSymbol() == ',' || i.getSymbol() == '-') {
                if (!tmpS.isEmpty()) {
                    words.add(new Word(tmpS));
                    tmpW.add(new Word(tmpS));
                    tmpS.clear();
                }
                tmpW.add(new SpecSymbol(i));
                tmpSS.add(new SpecSymbol(i));

                continue;
            }
            // finding end of sentence create new sentence with words from temporary words queue
            if (i.getSymbol() == '.' || i.getSymbol() == '!' || i.getSymbol() == '?') {
                if (tmpW.isEmpty() && tmpS.isEmpty())
                    continue;
                tmpW.add(new Word(tmpS));
                words.add(new Word(tmpS));
                tmpW.add(new SpecSymbol(i));
                sentences.add(new Sentence(tmpW));
                tmpS.clear();
                tmpW.clear();
                continue;
            }

            tmpS.add(i);

            //    System.out.println(i.getSymbol());
        }

        //Output lists of all parsed sentences and words
        System.out.println(sentences.size());
        for (Sentence i: sentences)
            System.out.println(i.toString());

        System.out.println(words.size());
        for (Textable i: words)
            System.out.println(i.toString());
    }

    /**
     * 3. Найти такое слово в первом предложении, которого нет ни в одном из остальных предложений.
     * Method for finding unique word in first sentence
     * that is absent in all other sentences
     * @return String Returns string version of such word
     */
    public String findUnique() {

        //pathing over all words in first sentence
        for (Textable j: sentences.get(0).getSentence()) {

            //flag shows is current word is unique
            boolean flag = false;

            //if word is separator like space or comma ignore it
            if (j instanceof SpecSymbol)
                continue;

            //compare current word with each word in other sentences of our text and finding the same one
            //assing flag to true
            for (int i = 1; i < sentences.size(); i++) {
                for (Textable k : sentences.get(i).getSentence()) {
                    if (j.equals(k))
                        flag = true;
                }
            }


            if (!flag)
                return j.toString();
        }
        return "Such word is absent";
    }

    public static void main(String[] args) {
        MyParser test = new MyParser();
        test.getTextFromFile("Final/src/text.txt");

        //Print unique word from first sentence
        System.out.println(test.findUnique());
    }
}
