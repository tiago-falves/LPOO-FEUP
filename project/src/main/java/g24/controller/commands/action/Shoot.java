package g24.controller.commands.action;

import g24.GameConfig;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.model.element.projectile.Gun;
import g24.model.element.projectile.Projectile;
import g24.model.utils.Positions;

import java.util.List;

public class Shoot extends Action {
    private Gun gun;
    private Positions positions;
    private boolean shootAutomatic;
    private int frameCounter = 0;

    public Shoot(Gun gun, Positions positions,boolean shootAutomatic){
        this.gun = gun;
        this.positions = positions;
        this.shootAutomatic = shootAutomatic;

    }
    public void executeAction(DIRECTION direction){
        if(!shootAutomatic){
            frameCounter++;
            if(frameCounter < (GameConfig.FPS )) {return;}
            frameCounter = 0;
        }else{
            long currentTime = System.currentTimeMillis();
            if(currentTime-this.gun.getLastTime() < 200) return;
            this.gun.setLastTime(currentTime);
        }
        shoot(direction);
    }

    private void shoot(DIRECTION direction){
        List<Projectile> projectiles = gun.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            if (!projectiles.get(i).exists()) {
                projectiles.get(i).setExists(true);
                projectiles.get(i).setPositions(positions.getCopy());
                projectiles.get(i).setDirection(direction);
                return;
            }
        }


    }
}
