package g24.controller.commands.interaction;


import g24.controller.map.RoomController;
import g24.model.element.objects.PowerUp;

public class UpdateGunCommand extends Interaction {

    @Override
    public void interact(PowerUp powerUp, RoomController room) {
        room.removeElement(room.getRoomModel().getPowerUps(), powerUp);
        room.nextGun();
    }
}
