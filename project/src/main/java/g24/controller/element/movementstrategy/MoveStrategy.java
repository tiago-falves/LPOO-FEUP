package g24.controller.element.movementstrategy;

import g24.model.utils.Positions;

public abstract class MoveStrategy {
    protected int frameCounter = 0;
    protected DIRECTION direction = DIRECTION.UP;

    public abstract Positions move(Positions positions);

    public DIRECTION getDirection() {return direction;}
}
