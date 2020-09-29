package g24.controller.map.rooms;

import g24.model.element.monsters.*;
import g24.model.element.objects.IncreaseDamage;
import g24.model.element.objects.IncreaseHealth;
import g24.model.element.objects.UpdateGun;
import g24.model.map.RoomModel;

public class EasyRoom {

    public void createRoom(RoomModel room){
        createMonsters(room);
        createObjects(room);
    }

    private void createObjects(RoomModel room){
        createTreasures(room);
    }
    private void createTreasures(RoomModel room){
        room.addTreasure(new IncreaseDamage(10,room.getHeight()-10,2));
        room.addTreasure(new IncreaseHealth(10,5,25));
        room.addTreasure(new UpdateGun(room.getWidth()-10,room.getHeight()-10,1));
    }

    private void createMonsters(RoomModel room){
        room.addMonster(new Bugs(5,5));
        room.addMonster(new AntiChrist(5,room.getHeight()-10));
        room.addMonster(new Bugs(room.getWidth()/2,5));
        room.addMonster(new Faces(room.getWidth()/2,room.getHeight()/2));
        room.addMonster(new Zombie(room.getWidth()/2 ,room.getHeight()/2 + 5));
        room.addMonster(new Charts(room.getWidth()/2 ,room.getHeight()/2 - 5));
    }

}
