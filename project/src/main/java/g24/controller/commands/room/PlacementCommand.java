package g24.controller.commands.room;

import g24.controller.map.RoomController;
import g24.model.element.Isaac;

public abstract class PlacementCommand {
    public abstract void execute(RoomController roomController, Isaac isaac);
}
