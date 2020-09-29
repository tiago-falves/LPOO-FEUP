package g24.controller.commands.interaction;


import g24.controller.map.RoomController;
import g24.model.element.objects.PowerUp;
import g24.model.utils.Health;

public class IncreaseHealthCommand extends Interaction {
    private int value;

    public IncreaseHealthCommand(int value){
        this.value = value;
    }

    @Override
    public void interact(PowerUp powerUp, RoomController room) {
        Health health = room.getIsaac().getHealth();
        room.removeElement(room.getRoomModel().getPowerUps(), powerUp);
        room.getHealthController().increaseHealth(value);
        health.increase(value);
    }
}
