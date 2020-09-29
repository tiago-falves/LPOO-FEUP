package g24.controller.element;

import g24.controller.element.movementstrategy.DIRECTION;
import g24.model.utils.Position;
import g24.model.utils.Positions;
import g24.model.element.projectile.Projectile;

public class ProjectileController {
    private Projectile projectile;

    public ProjectileController(Projectile projectile) {
        this.projectile = projectile;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    private void shiftToInitialPositions(Position initial) {
        projectile.getPositions().shiftTo(initial);
        projectile.setJustCreated(false);
    }


    public void shoot(Positions holderPositions){

        if(!projectile.exists()) return;
        DIRECTION command = projectile.getDirection();
        switch (command){
            case UP:
                if(projectile.justCreated()) shiftToInitialPositions(holderPositions.getMiddlePosition());
                projectile.shootUp();
                break;
            case DOWN:
                if(projectile.justCreated()) shiftToInitialPositions(holderPositions.getMiddlePosition());
                projectile.shootDown();
                break;
            case LEFT:
                if(projectile.justCreated()) shiftToInitialPositions(holderPositions.getFirstPosition());
                projectile.shootLeft();
                break;
            case RIGHT:
                if(projectile.justCreated()) shiftToInitialPositions(holderPositions.getLastPosition());
                projectile.shootRight();
                break;
        }
    }

    public void increaseDamage(int value){
        this.projectile.setDamage(this.projectile.getDamage() + value);
    }



}
