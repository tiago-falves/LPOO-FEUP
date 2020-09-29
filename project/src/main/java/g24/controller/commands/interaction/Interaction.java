package g24.controller.commands.interaction;

import g24.controller.map.RoomController;
import g24.model.element.objects.PowerUp;

public abstract class Interaction {
    public abstract void interact(PowerUp powerUp, RoomController room);
}
