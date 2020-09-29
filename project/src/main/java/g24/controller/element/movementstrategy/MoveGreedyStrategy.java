package g24.controller.element.movementstrategy;

import g24.GameConfig;
import g24.model.element.Isaac;
import g24.model.utils.Position;
import g24.model.utils.Positions;

public class MoveGreedyStrategy extends MoveStrategy{
    private Isaac isaac;
    public MoveGreedyStrategy(Isaac isaac){
        this.isaac = isaac;
    }

    @Override
    public Positions move(Positions positions) {

        Positions destiny = isaac.getPositions();
        frameCounter++;
        if(frameCounter < (GameConfig.FPS/6)) {
            return positions;
        }
        frameCounter = 0;

        direction = positions.getMoveDirection(destiny.getMiddlePosition());
        return positions.moveTo(direction);
    }
}
