package g24.controller.commands.user;

import g24.controller.element.CollisionHandler;
import g24.controller.map.RoomController;
import g24.model.element.Element;

public abstract class MoveCommand extends UserCommand {
    protected Element element;
    protected RoomController room;
    protected CollisionHandler collisionHandler;

    public MoveCommand(Element element, RoomController room,CollisionHandler collisionHandler){
        this.element = element;
        this.room = room;
        this.collisionHandler = collisionHandler;
    }
    
    public abstract void execute();

}
