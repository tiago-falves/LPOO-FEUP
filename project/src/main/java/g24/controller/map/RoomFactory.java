package g24.controller.map;

import g24.GameConfig;
import g24.controller.map.rooms.*;
import g24.model.element.objects.*;
import g24.model.element.projectile.*;
import g24.model.map.RoomType;
import g24.model.utils.Health;
import g24.model.element.Isaac;
import g24.model.map.Compass;
import g24.model.map.RoomModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomFactory {
    private final Random random;
    private static List<Gun> guns;
    private static int startingX;
    private static int startingY;

    public RoomFactory() {
        this.random = new Random();
        guns = createGuns();
        startingX = GameConfig.ROOM_WIDTH / 2;
        startingY = GameConfig.ROOM_HEIGHT / 2;
    }

    public RoomModel createRoom(Isaac isaac, Compass doorAccess, RoomType roomType) {

        RoomModel room = new RoomModel(isaac, GameConfig.ROOM_WIDTH, GameConfig.ROOM_HEIGHT, doorAccess);
        room.addGuns(guns);

        createOutline(room);
        switch(roomType) {
            case START:
                break;
            case TRAP:
                TrapRoom trapRoom = new TrapRoom();
                trapRoom.createRoom(room);
                break;
            case ENEMY_EASY:
                EasyRoom easy = new EasyRoom();
                easy.createRoom(room);
                break;
            case ENEMY_MEDIUM:
                MediumRoom mediumRoom = new MediumRoom();
                mediumRoom.createRoom(room);
                break;
            case ENEMY_HARD:
                HardRoom hardRoom = new HardRoom();
                hardRoom.createRoom(room);
                break;
            case BOSS:
                BossRoom bossRoom = new BossRoom();
                bossRoom.createRoom(room);
                break;
        }
        room.addAllInObjects();

        return room;
    }

    private void createOutline(RoomModel roomModel) {
        int doorSpaceHor = (roomModel.getWidth() - GameConfig.DOOR_SPAN_HOR) / 2;
        for (int c = 0; c < roomModel.getWidth(); c++) {
            if(c >= doorSpaceHor && c < roomModel.getWidth() - doorSpaceHor && roomModel.getDoorAccess().isNorth()) {
                roomModel.addDoor(new Door(c, 0));
            } else {
                roomModel.addWall(new Wall(c, 0));
            }

            if(c >= doorSpaceHor && c < roomModel.getWidth() - doorSpaceHor && roomModel.getDoorAccess().isSouth()) {
                roomModel.addDoor(new Door(c, roomModel.getHeight() - 1));
            } else {
                roomModel.addWall(new Wall(c, roomModel.getHeight() - 1));
            }

        }

        int doorSpaceVert = (roomModel.getHeight() - GameConfig.DOOR_SPAN_VERT) / 2;
        for (int r = 1; r < roomModel.getHeight() - 1; r++) {
            if(r >= doorSpaceVert && r < roomModel.getHeight() - doorSpaceVert && roomModel.getDoorAccess().isWest()) {
                roomModel.addDoor(new Door(0, r));
            } else {
                roomModel.addWall(new Wall(0, r));
            }

            if(r >= doorSpaceVert && r < roomModel.getHeight() - doorSpaceVert && roomModel.getDoorAccess().isEast()) {
                roomModel.addDoor(new Door(roomModel.getWidth() - 1, r));
            } else {
                roomModel.addWall(new Wall(roomModel.getWidth() - 1, r));
            }
        }

    }

    private List<Gun> createGuns(){
        List<Gun> guns = new ArrayList<>();
        Gun bulletGun = new Gun(startingX, startingY, GUNTYPE.BULLET);
        Gun grenadeGun = new Gun(startingX, startingY, GUNTYPE.GRENADE);
        guns.add(bulletGun);
        guns.add(grenadeGun);

        return guns;
    }

    public Isaac createIsaac() {
        return new Isaac(startingX, startingY / 2, new Health(100), guns.get(0));
    }

}
