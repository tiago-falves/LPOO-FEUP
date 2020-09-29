package g24.controller.map.rooms;

import g24.model.element.monsters.*;
import g24.model.element.objects.Hole;
import g24.model.map.RoomModel;

import java.util.Random;

public class TrapRoom {

    public void createRoom(RoomModel room){
        createTraps(room);
        createMonsters(room);
    }

    private void createTraps(RoomModel room){
        Random random = new Random();

        for (int i = 10; i < room.getWidth()-10; i+= 10) {
            for (int j = 5; j < room.getHeight()-5; j+= 5) {
                int xVar = random.nextInt(2);
                int yVar = random.nextInt(3);
                room.addTreasure(new Hole(i+xVar,j+ yVar,20));
                room.addTreasure(new Hole(i+xVar,j+1+yVar,20));
            }
        }
    }

    private void createMonsters(RoomModel room){
        room.addGunMonster(new Morty(5,5,room.getIsaac()));
        room.addGunMonster(new Morty(5,room.getHeight()-2,room.getIsaac()));
        room.addGunMonster(new Morty(room.getWidth()-8,5,room.getIsaac()));
        room.addGunMonster(new Morty(room.getWidth()-8,room.getHeight()-2,room.getIsaac()));

    }

}
