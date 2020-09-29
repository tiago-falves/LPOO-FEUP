package g24.controller.commands.interaction;


import g24.controller.map.RoomController;
import g24.model.element.Isaac;
import g24.model.element.objects.PowerUp;

public class IncreaseDamageCommand extends Interaction {
    private int value;

    public IncreaseDamageCommand(int value){
        this.value = value;
    }

    @Override
    public void interact(PowerUp powerUp, RoomController room) {
        Isaac isaac = room.getIsaac();
        room.removeElement(room.getRoomModel().getPowerUps(), powerUp);
        room.increaseBulletsDamage(isaac,value);
    }
}
