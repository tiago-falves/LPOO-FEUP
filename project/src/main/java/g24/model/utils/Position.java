package g24.model.utils;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position left() {
        return new Position(x - 1, y);
    }

    public Position right() {
        return new Position(x + 1, y);
    }

    public Position up() {
        return new Position(x, y - 1);
    }

    public Position down() {
        return new Position(x, y + 1);
    }

    public boolean collide(Position position) {
        return getX() == position.getX() &&
                getY() == position.getY();
    }
    public boolean near(Position position) {
        return Math.abs(getX()-position.getX()) <= 1 &&
                Math.abs(getY()-position.getY()) <= 1;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getX() == position.getX() &&
                getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
