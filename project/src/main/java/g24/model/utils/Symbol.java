package g24.model.utils;

public class Symbol {

    private String symbol;
    private int size;
    private String color;

    public Symbol(String symbol,String color) {
        this.symbol = symbol;
        this.size = symbol.length();
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColor(){
        return color;
    }


}
