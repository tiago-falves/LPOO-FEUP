package g24.model.map;

import g24.controller.element.movementstrategy.DIRECTION;
import g24.controller.commands.user.COMMAND;
import g24.model.Model;
import g24.model.element.Element;
import g24.model.element.Isaac;
import g24.model.element.monsters.GunMonster;
import g24.model.element.objects.Door;
import g24.model.element.objects.IndestructibleObject;
import g24.model.element.objects.PowerUp;
import g24.model.element.objects.Wall;
import g24.model.element.monsters.Monster;
import g24.model.element.projectile.Gun;

import java.util.ArrayList;
import java.util.List;

public class RoomModel extends Model {

    private int width;
    private int height;
    private int frameCounter;
    private boolean isFinished;
    private DIRECTION currentDirection;
    private Compass doorAccess;
    private Isaac isaac;
    private List<Wall> walls = new ArrayList<>();
    private List<Door> doors = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();
    private List<GunMonster> GunMonsters = new ArrayList<>();
    private List<IndestructibleObject> indestructibleObjects = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private List<Gun> guns = new ArrayList<>();

    public RoomModel(Isaac isaac, int width, int height, Compass doorAccess) {
        this.width = width;
        this.height = height;
        this.isaac = isaac;
        this.doorAccess = doorAccess;
        this.isFinished = false;
        this.currentDirection = DIRECTION.UP;
        this.frameCounter = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Isaac getIsaac() {
        return isaac;
    }

    public void setIsaac(Isaac isaac) {
        this.isaac = isaac;
    }

    public Compass getDoorAccess() {
        return doorAccess;
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }

    public void addGunMonster(GunMonster monster) {
        this.GunMonsters.add(monster);
        this.monsters.add(monster);
    }

    public void addIndestructibleObject(IndestructibleObject inObject) {
        this.indestructibleObjects.add(inObject);
    }

    public void addAllInObjects() {
        this.indestructibleObjects.addAll(this.walls);
        this.indestructibleObjects.addAll(this.doors);
    }

    public List<Monster> getMonsters() {
        return this.monsters;
    }

    public List<IndestructibleObject> getIndestructible() {
        return this.indestructibleObjects;
    }

    public List<Wall> getWalls() {
        return this.walls;
    }

    public void addWall(Wall wall) {
        this.walls.add(wall);
    }

    public void addDoor(Door door) {
        this.doors.add(door);
    }

    public List<Door> getDoors() {
        return this.doors;
    }

    public void addGun(Gun gun) {
        this.guns.add(gun);
    }

    public void addGuns(List<Gun> guns) {
        for(Gun gun : guns)
            addGun(gun);
    }

    public List<Gun> getGuns() {
        return guns;
    }

    public void setGuns(List<Gun> guns) {
        this.guns = guns;
    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<Element>();
        elements.add(isaac);
        elements.addAll(walls);
        elements.addAll(monsters);
        elements.addAll(doors);
        elements.addAll(powerUps);
        return elements;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public DIRECTION getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(COMMAND direction) {
        switch (direction){
            case UP: this.currentDirection = DIRECTION.UP; break;
            case DOWN: this.currentDirection = DIRECTION.DOWN;break;
            case LEFT: this.currentDirection = DIRECTION.LEFT;break;
            case RIGHT: this.currentDirection = DIRECTION.RIGHT;break;
        }
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void addTreasure(PowerUp powerUp) {
        this.powerUps.add(powerUp);
    }

    public List<GunMonster> getGunMonsters() {
        return GunMonsters;
    }

    public void setGunMonsters(List<GunMonster> gunMonsters) {
        GunMonsters = gunMonsters;
    }

}
