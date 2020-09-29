package g24.controller.element.movementstrategy;

import g24.model.utils.Positions;
import g24.GameConfig;

import java.util.Random;

public class MoveRandomStrategy extends MoveStrategy{

    @Override
    public Positions move(Positions positions) {

        frameCounter++;
        if(frameCounter < (GameConfig.FPS/3)) {
            return positions;
        }
        frameCounter = 0;

        Random random = new Random();
        int index = random.nextInt(4);
        direction= DIRECTION.values()[index];

        switch (direction) {
            case LEFT: return positions.left();
            case RIGHT: return positions.right();
            case UP: return positions.up();
            case DOWN: return positions.down();
        }

        return positions;
    }
}
