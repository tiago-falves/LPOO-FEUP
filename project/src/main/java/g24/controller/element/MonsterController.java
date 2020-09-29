package g24.controller.element;

import g24.GameConfig;
import g24.controller.commands.action.Action;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.controller.element.movementstrategy.MoveStrategy;
import g24.controller.map.RoomController;
import g24.model.element.monsters.Monster;
import g24.model.element.objects.IndestructibleObject;
import g24.model.element.objects.PowerUp;
import g24.model.map.RoomModel;
import g24.model.utils.Positions;

import java.util.List;

public class MonsterController {
    private Monster monster;


    public MonsterController(Monster monster) {
        this.monster = monster;
    }

    public void handle(RoomController room,CollisionHandler cl){
        move(room,cl);
        executeAction();
    }

    private void move(RoomController roomController,CollisionHandler cl){
        MoveStrategy moveStrategy = monster.getMoveStrategy();
        Positions nextPositions = moveStrategy.move(monster.getPositions());
        if(roomController.isInsideBoundaries(nextPositions)) {
            List<PowerUp> indestructible  = roomController.getRoomModel().getPowerUps();
            boolean isCollidingTrap = cl.isCollidingElement(nextPositions,indestructible);
            boolean isCollidingIsac = roomController.getIsaac().getPositions().collide(nextPositions);

            if(!isCollidingTrap && !isCollidingIsac){
                monster.setPositions(nextPositions);
            }
        }
    }

    private void executeAction(){
        Action action = monster.getAction();
        DIRECTION direction = monster.getMoveStrategy().getDirection();
        action.executeAction(direction);
    }



}
