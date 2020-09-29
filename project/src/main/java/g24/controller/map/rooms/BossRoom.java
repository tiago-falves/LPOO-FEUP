package g24.controller.map.rooms;

import g24.model.element.monsters.Boss;
import g24.model.element.objects.IncreaseDamage;
import g24.model.element.objects.IncreaseHealth;
import g24.model.element.objects.UpdateGun;
import g24.model.map.RoomModel;

public class BossRoom {

    public void createRoom(RoomModel room){
        room.addGunMonster(new Boss(room.getWidth()-30,room.getHeight()-5,room.getIsaac()));
        createObjects(room);
    }

    private void createObjects(RoomModel room){
        createTreasures(room);
    }
    private void createTreasures(RoomModel room){
        room.addTreasure(new IncreaseDamage(10,room.getHeight()-10,1));
        room.addTreasure(new IncreaseHealth(10,5,1));
        room.addTreasure(new UpdateGun(room.getWidth()-10,room.getHeight()-10,1));
    }

}
