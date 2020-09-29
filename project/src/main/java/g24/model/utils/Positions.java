package g24.model.utils;

import g24.controller.element.movementstrategy.DIRECTION;

import java.util.ArrayList;
import java.util.List;

public class Positions {

    private List<Position> positionList;

    public Positions() {
        this.positionList = new ArrayList<>();
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public Position getFirstPosition(){
        return positionList.get(0);
    }

    public Position getLastPosition(){
        return positionList.get(positionList.size()-1);
    }

    public Position getMiddlePosition() {
        return positionList.get(positionList.size()/2);
    }

    public Positions getCopy() {
        Positions copy = new Positions();

        for(Position p : positionList) {
            copy.addPosition(new Position(p.getX(), p.getY()));
        }

        return copy;
    }

    public boolean collide(Positions otherPositions){
        for (Position otherPosition : otherPositions.getPositionList()){
            for(Position position : positionList){
                if (position.collide(otherPosition))
                    return true;
            }
        }
        return false;
    }

    public boolean near(Positions otherPositions){
        for (Position otherPosition : otherPositions.getPositionList()){
            for(Position position : positionList){
                if (position.near(otherPosition))
                    return true;
            }
        }
        return false;
    }

    public void addPosition(Position position){
        positionList.add(position);
    }

    public void shiftTo(Position positionTo) {
        Position firstPosition = getFirstPosition();

        int deltaX = positionTo.getX() - firstPosition.getX();
        int deltaY = positionTo.getY() - firstPosition.getY();

        for(int i = 0; i < positionList.size(); i++) {
            Position p = positionList.get(i);
            positionList.set(i, new Position(p.getX() + deltaX, p.getY() + deltaY));
        }
    }

    public Positions moveTo(Position destiny){
        Position position = getMiddlePosition();
        int deltaX = destiny.getX() - position.getX();
        int deltaY = destiny.getY() - position.getY();
        if(deltaX > 0) return right();
        if(deltaX < 0) return left();
        if(deltaY > 0) return down();
        if(deltaY < 0) return up();
        return this;
    }

    public Positions moveTo(DIRECTION direction){
        switch (direction){
            case UP: return up();
            case DOWN: return down();
            case LEFT: return left();
            case RIGHT: return right();
        }
        return this;
    }

    public DIRECTION getMoveDirection(Position destiny){
        Position position = getMiddlePosition();
        int deltaX = destiny.getX() - position.getX();
        int deltaY = destiny.getY() - position.getY();
        if(deltaX > 0) return DIRECTION.RIGHT;
        if(deltaX < 0) return DIRECTION.LEFT;
        if(deltaY > 0) return DIRECTION.DOWN;
        if(deltaY < 0) return DIRECTION.UP;

        return DIRECTION.UP;
    }

    public Positions left() {
        Positions newPositions = new Positions();
        for (int i = 0; i < positionList.size(); i++) {
            newPositions.addPosition(positionList.get(i).left());
        }

        return newPositions;
    }

    public Positions right() {
        Positions newPositions = new Positions();
        for (int i = 0; i < positionList.size(); i++) {
            newPositions.addPosition(positionList.get(i).right());
        }

        return newPositions;
    }

    public Positions up() {
        Positions newPositions = new Positions();

        for (int i = 0; i < positionList.size(); i++) {
            newPositions.addPosition(positionList.get(i).up());
        }
        return newPositions;
    }

    public Positions down() {
        Positions newPositions = new Positions();

        for (int i = 0; i < positionList.size(); i++) {
            newPositions.addPosition(positionList.get(i).down());
        }
        return newPositions;
    }

}
