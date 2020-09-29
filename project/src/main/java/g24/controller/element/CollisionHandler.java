package g24.controller.element;

import g24.controller.commands.interaction.Interaction;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.controller.map.RoomController;
import g24.model.element.Element;
import g24.model.element.Isaac;
import g24.model.element.monsters.GunMonster;
import g24.model.element.monsters.Monster;
import g24.model.element.objects.Door;
import g24.model.element.objects.IndestructibleObject;
import g24.model.element.objects.PowerUp;
import g24.model.element.projectile.Projectile;
import g24.model.map.RoomModel;
import g24.model.utils.Positions;

import java.util.List;

public class CollisionHandler {
    private RoomController roomController;

    public CollisionHandler() {
        this.roomController = null;
    }

    public CollisionHandler(RoomController room){
        this.roomController = room;
    }

    public void setRoom(RoomController roomController) {
        this.roomController = roomController;
    }

    public void handleCollisions(){
        handleIsaacCollisions();
        handleBulletCollisions();
    }

    private void handleBulletCollisions() {
        handleMonsterBulletCollision();
        handleBulletIndestructibleCollision();
        handleIsaacBulletCollision();
    }

    private void handleMonsterBulletCollision() {
        Isaac isaac = roomController.getIsaac();
        for (Monster monster : roomController.getRoomModel().getMonsters()){
            Projectile projectile = (Projectile) getCollidingElement(monster.getPositions(),isaac.getGun().getProjectiles());
            if(projectile != null && projectile.exists()){
                projectile.setExists(false);
                monster.getHealth().decrease(projectile.getDamage());
                if(monster.getHealth().isZero()){
                    roomController.getScoreController().increaseScore(monster.getPower()*5);
                    roomController.removeElement(roomController.getRoomModel().getMonsters(),monster);
                    break;
                }
            }
        }
    }

    private void handleIsaacBulletCollision(){
        for(GunMonster monster1 : roomController.getRoomModel().getGunMonsters()){
            Projectile projectile = (Projectile) getCollidingElement(roomController.getRoomModel().getIsaac().getPositions(),monster1.getGun().getProjectiles());
            if(projectile != null && projectile.exists()){
                projectile.setExists(false);
                roomController.getHealthController().decreaseHealth(projectile.getDamage());
                roomController.getScoreController().decreaseScore(projectile.getDamage() / 3);
                roomController.getIsaac().getHealth().decrease(projectile.getDamage());
                if(roomController.getRoomModel().getIsaac().getHealth().isZero()){
                    roomController.finishRoom(false);
                    break;
                }
            }
        }
    }

    private void handleBulletIndestructibleCollision(){
        Isaac isaac = roomController.getRoomModel().getIsaac();
        for (IndestructibleObject indestructibleObject : roomController.getRoomModel().getIndestructible()){
            handleProjectile(indestructibleObject.getPositions(),isaac.getGun().getProjectiles());
            for(GunMonster monster : roomController.getRoomModel().getGunMonsters())
                handleProjectile(indestructibleObject.getPositions(),monster.getGun().getProjectiles());
        }
    }

    private void handleProjectile(Positions positions, List<Projectile> projectiles){
        Projectile projectile = (Projectile) getCollidingElement(positions,projectiles);
        if(projectile != null){
            projectile.setExists(false);
        }
    }

    private void handleIsaacCollisions() {
        Monster monster = (Monster) getNearElement(roomController.getRoomModel().getIsaac().getPositions(),roomController.getRoomModel().getMonsters());
        if(monster != null){
            roomController.getHealthController().decreaseHealth(monster.getPower());
            roomController.getScoreController().decreaseScore(monster.getPower() / 5);
            roomController.getRoomModel().getIsaac().getHealth().decrease(monster.getPower());
            if(roomController.getRoomModel().getIsaac().isDead()){
                roomController.finishRoom(false);
            }
        }
        PowerUp powerUp = (PowerUp) getCollidingElement(roomController.getRoomModel().getIsaac().getPositions(),roomController.getRoomModel().getPowerUps());
        if(powerUp != null){
            Interaction interaction = powerUp.getInteraction();
            interaction.interact(powerUp,roomController);

        }
    }

    public Element getCollidingElement(Positions positions, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPositions().collide(positions))
                return element;
        return null;
    }

    public boolean isCollidingElement(Positions positions, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPositions().collide(positions))
                return true;
        return false;
    }

    public Element getNearElement(Positions positions, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPositions().near(positions))
                return element;
        return null;
    }

    public boolean handleDoorCollisionDown(RoomController room, Positions newPositions){
        for(Door door : room.getRoomModel().getDoors()) {
            if(door.getPositions().getFirstPosition().getY() == room.getRoomModel().getHeight()-1 && door.getPositions().collide(newPositions)) {
                room.setChangeRoom(true);
                room.setChangeRoomDirection(DIRECTION.DOWN);
                return true;
            }
        }
        return false;
    }
    public boolean handleDoorCollisionLeft(RoomController room, Positions newPositions){
        for(Door door : room.getRoomModel().getDoors()) {
            if(door.getPositions().getFirstPosition().getX() == 0 && door.getPositions().collide(newPositions)) {
                room.setChangeRoom(true);
                room.setChangeRoomDirection(DIRECTION.LEFT);
                return true;
            }
        }
        return false;
    }

    public boolean handleDoorCollisionRight(RoomController room, Positions newPositions){
        for(Door door : room.getRoomModel().getDoors()) {
            if(door.getPositions().getFirstPosition().getX() == room.getRoomModel().getWidth()-1 && door.getPositions().collide(newPositions)) {
                room.setChangeRoom(true);
                room.setChangeRoomDirection(DIRECTION.RIGHT);
                return true;
            }
        }
        return false;
    }

    public boolean handleDoorCollisionUp(RoomController room, Positions newPositions){
        for(Door door : room.getRoomModel().getDoors()) {
            if(door.getPositions().getFirstPosition().getY() == 0 && door.getPositions().collide(newPositions)) {
                room.setChangeRoom(true);
                room.setChangeRoomDirection(DIRECTION.UP);
                return true;
            }
        }
        return false;
    }


    public boolean collidingMonster(Positions positions) {
        Monster monster = (Monster) getCollidingElement(positions, roomController.getRoomModel().getMonsters());
        if(monster != null){
            return true;
        }
        return false;
    }


}
