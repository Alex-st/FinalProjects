/**
 * Class containing spaces, commas and dots, etc
 * Created by alex on 5/26/15.
 */
//
class SpecSymbol extends Textable {
    private Symbol specSymbol;

    public SpecSymbol(Symbol s) {
        specSymbol = s;
    }

    public String toString() {
        return ""+specSymbol.getSymbol();
    }

    //equals for special symbols doesn't matter for us, that is why it is set to false
    public boolean equals(Object o) {
        return false;
    }
}