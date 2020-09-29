package g24.model.element.projectile;

import g24.controller.element.movementstrategy.DIRECTION;
import g24.model.element.Element;
import g24.model.utils.Symbol;

public abstract class Projectile extends Element {
    private int damage;
    private int velocity;
    private boolean exists;
    private boolean justCreated;
    private DIRECTION direction;

    public Projectile(int x, int y, Symbol symbol, int damage,int velocity) {
        super(x, y, symbol);
        this.damage = damage;
        this.velocity = velocity;
        this.exists = false;
        this.direction = DIRECTION.UP;
    }

    public int getDamage(){
        return damage;
    }
    public void setDamage(int damage){this.damage =  damage;}

    public boolean exists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.justCreated = exists;
        this.exists = exists;
    }

    public boolean justCreated() {
        return this.justCreated;
    }

    public void setJustCreated(boolean justCreated) {
        this.justCreated = justCreated;
    }

    public void shootUp(){this.setPositions(this.getPositions().up());}
    public void shootDown(){this.setPositions(this.getPositions().down());}
    public void shootLeft(){this.setPositions(this.getPositions().left());}
    public void shootRight(){this.setPositions(this.getPositions().right());}

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public int getVelocity() {return velocity;}
    public void setVelocity(int velocity) {this.velocity = velocity;}
}
