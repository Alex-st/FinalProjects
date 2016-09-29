import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represent one pure symbol of text
 *
 * Created by alex on 5/12/15.
 */

public class Symbol {
    private char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Static method for creating List of symbols of any length
     * @param chars Vararg for input chars
     * @return List<Symbol> Returns List of symbols.
     */
    public static List<Symbol> toList(char... chars) {
        List<Symbol> tmp = new ArrayList<Symbol>();

        for (char i: chars) {
            tmp.add(new Symbol(i));
        }

        return tmp;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean equals(Symbol s) {
        return this.getSymbol() == s.getSymbol();
    }
}

