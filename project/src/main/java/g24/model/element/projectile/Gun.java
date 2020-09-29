package g24.model.element.projectile;

import g24.GameConfig;

import java.util.ArrayList;
import java.util.List;

public class Gun {
    private List<Projectile> projectiles;
    private int frameCounter = 0;
    private long lastTime;

    public Gun(int x,int y,GUNTYPE type) {
        if (type == GUNTYPE.BULLET) this.projectiles = createProjectiles(x,y);
        else if((type == GUNTYPE.GRENADE)) this.projectiles = createGrenades(x,y);
        lastTime = System.currentTimeMillis();

    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
    public void setProjectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public int getFrameCounter() {
        return frameCounter;
    }
    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
    }
    public void incrementFrameCounter(){
        this.frameCounter++;
    }

    public int getVelocity(){
        if(projectiles.size()>0)  return projectiles.get(0).getVelocity();
        return GameConfig.FPS/1000;
    }

    private List<Projectile> createProjectiles(int x,int y){
        List<Projectile> projectiles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            projectiles.add(new Bullet(x,y));
        }
        return projectiles;
    }

    private List<Projectile> createGrenades(int x,int y){
        List<Projectile> projectiles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            projectiles.add(new Grenade(x,y));
        }
        return projectiles;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
}
