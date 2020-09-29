package g24.controller.map;

import g24.controller.element.CollisionHandler;
import g24.controller.element.HealthController;
import g24.controller.element.MonsterController;
import g24.controller.element.ProjectileController;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.model.element.*;
import g24.model.element.monsters.GunMonster;
import g24.model.element.projectile.Gun;
import g24.model.element.projectile.Projectile;
import g24.model.element.monsters.Monster;
import g24.model.map.RoomModel;
import g24.model.utils.Positions;

import java.util.List;

public class RoomController {
    private RoomModel roomModel;
    private HealthController healthController;
    private ScoreController scoreController;
    private CollisionHandler collisionHandler;
    private boolean changeRoom;
    private boolean won;
    private DIRECTION changeRoomDirection;

    public RoomController(RoomModel roomModel, HealthController healthController, ScoreController scoreController, CollisionHandler collisionHandler) {
        this.roomModel = roomModel;
        this.healthController = healthController;
        this.scoreController = scoreController;
        this.collisionHandler = collisionHandler;
        this.changeRoom = false;
        this.won = false;
        this.changeRoomDirection = DIRECTION.UP;

        collisionHandler.setRoom(this);
    }

    public void nextIteration() {
        handleBullets();
        handleMonsters();
        handleCollisions();
    }

    private void handleBullets() {
        Isaac isaac = getIsaac();
        shoot(isaac.getGun(),isaac.getPositions());
        for (GunMonster monster : roomModel.getGunMonsters()){
            shoot(monster.getGun(),monster.getPositions());
        }
    }

    private void shoot(Gun gun,Positions positions){
        gun.incrementFrameCounter();
        if(gun.getFrameCounter() < (gun.getVelocity())) {return;}
        gun.setFrameCounter(0);
        for (Projectile projectile : gun.getProjectiles()){
            ProjectileController projectileController = new ProjectileController(projectile);
            projectileController.shoot(positions);
        }

    }

    private void handleMonsters() {
        for (Monster monster: this.roomModel.getMonsters()) {
            MonsterController monsterController = new MonsterController(monster);
            monsterController.handle(this, collisionHandler);
        }
    }

    public boolean isInsideBoundaries(Positions positions) {
        boolean isInsideX = positions.getFirstPosition().getX() > 0 && positions.getLastPosition().getX() < roomModel.getWidth()-1;
        boolean isInsideY = positions.getFirstPosition().getY() > 0 && positions.getLastPosition().getY() < roomModel.getHeight()-1;
        return isInsideX && isInsideY;
    }

    private void handleCollisions() {
        collisionHandler.handleCollisions();
    }

    public void increaseBulletsDamage(Isaac isaac,int value){
        for (Projectile projectile : isaac.getGun().getProjectiles()){
            ProjectileController projectileController = new ProjectileController(projectile);
            projectileController.increaseDamage(value);
        }
    }

    public void nextGun(){
        List<Gun> gunsList = this.roomModel.getGuns();
        if(gunsList.size() > 1){
            gunsList.remove(0);
            this.roomModel.getIsaac().setGun(gunsList.get(0));
        }
    }

    public boolean changeRoom() {
        return changeRoom;
    }

    public void setChangeRoom(boolean changeRoom) {
        this.changeRoom = changeRoom;
    }

    public DIRECTION getChangeRoomDirection() {
        return changeRoomDirection;
    }

    public void setChangeRoomDirection(DIRECTION changeRoomDirection) {
        this.changeRoomDirection = changeRoomDirection;
    }

    public void removeElement(List<? extends Element> elements, Element element) {
        elements.remove(element);

    }

    public HealthController getHealthController() {
        return healthController;
    }

    public ScoreController getScoreController() {
        return scoreController;
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }

    public Isaac getIsaac() {
        return this.roomModel.getIsaac();
    }

    public boolean isFinished() {
        return this.roomModel.isFinished();
    }

    public void finishRoom(boolean won) {
        this.won = won;
        this.roomModel.setFinished(true);
    }

    public boolean hasWon() {
        return won;
    }

    public void reset() {
        won = false;
        changeRoom = false;
    }

}
