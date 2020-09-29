package g24.model.element;

import g24.model.Model;
import g24.model.utils.Position;
import g24.model.utils.Positions;
import g24.model.utils.Symbol;

public abstract class Element extends Model {
    protected Positions positions;
    private Symbol symbol;

    public Element(int x, int y, Symbol symbol) {
        this.symbol = symbol;
        this.positions = createPositions(x,y);

    }

    public Positions getPositions() {
        return positions;
    }
    public void setPositions(Positions newPositions) {
         this.positions = newPositions;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    protected Positions createPositions(int x,int y){
        Positions positionsInt = new Positions();
        for (int i = 0; i < symbol.getSize(); i++) {
            positionsInt.addPosition(new Position(x + i,y));
        }
        return positionsInt;
    }
}
