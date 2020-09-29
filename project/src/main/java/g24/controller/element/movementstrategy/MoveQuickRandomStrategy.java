package g24.controller.element.movementstrategy;

import g24.GameConfig;
import g24.model.utils.Positions;

import java.util.Random;

public class MoveQuickRandomStrategy extends MoveStrategy{

    @Override
    public Positions move(Positions positions) {
        frameCounter++;
        if(frameCounter < (GameConfig.FPS/6)) {
            return positions;
        }
        frameCounter = 0;

        direction = getRandomDirection();


        switch (direction) {
            case LEFT: return positions.left().left();
            case RIGHT:return positions.right().right();
            case DOWN: return positions.down().down();
            case UP: return positions.up().up();
        }
        return positions;
    }

    public DIRECTION getRandomDirection(){
        Random random = new Random();
        int index = random.nextInt(4);
        return DIRECTION.values()[index];
    }


}
