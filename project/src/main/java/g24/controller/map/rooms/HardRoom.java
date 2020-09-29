package g24.controller.map.rooms;

import g24.model.element.monsters.*;
import g24.model.element.objects.IncreaseDamage;
import g24.model.element.objects.IncreaseHealth;
import g24.model.element.objects.UpdateGun;
import g24.model.map.RoomModel;

public class HardRoom {

    public void createRoom(RoomModel room){
        createMonsters(room);
        createObjects(room);
    }

    private void createObjects(RoomModel room){
        createTreasures(room);
    }
    private void createTreasures(RoomModel room){
        room.addTreasure(new IncreaseDamage(10,room.getHeight()-15,5));
        room.addTreasure(new IncreaseHealth(10,5,1));
        room.addTreasure(new UpdateGun(room.getWidth()-10,room.getHeight()-10,1));
    }

    private void createMonsters(RoomModel room){

        room.addMonster(new Pervert(5,room.getHeight()-5,room.getIsaac()));
        room.addMonster(new Pervert(room.getWidth()-10,room.getHeight()-5,room.getIsaac()));
        room.addMonster(new Faces(room.getWidth()/2,room.getHeight()/2));
        createGunMonsters(room);
    }

    private void createGunMonsters(RoomModel room){
        room.addMonster(new Morty(5,5,room.getIsaac()));
        room.addMonster(new Morty(room.getWidth()-5,5,room.getIsaac()));
        room.addGunMonster(new Morty(20,room.getHeight()-10,room.getIsaac()));
        room.addMonster(new Morty(room.getWidth()/2,room.getHeight()-10,room.getIsaac()));
        room.addMonster(new Morty(room.getWidth()/2,10,room.getIsaac()));
    }

}
