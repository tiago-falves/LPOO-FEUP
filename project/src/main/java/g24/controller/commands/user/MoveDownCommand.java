package g24.controller.commands.user;

import g24.controller.element.CollisionHandler;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.controller.map.RoomController;
import g24.model.element.Element;
import g24.model.element.objects.Door;
import g24.model.utils.Positions;

public class MoveDownCommand extends MoveCommand {

    public MoveDownCommand(Element element, RoomController room,CollisionHandler collisionHandler){
        super(element,room,collisionHandler);
    }
    
    public void execute(){
        Positions positions = element.getPositions().down();
        if(collisionHandler.handleDoorCollisionDown(room, positions)) return;
        if(collisionHandler.collidingMonster(positions)) return;

        if (room.isInsideBoundaries(positions)){
            element.setPositions(positions);
        }
    }

}
