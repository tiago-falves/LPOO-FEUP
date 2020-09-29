package g24.model.hud;

import g24.controller.observer.IsaacHealthObserver;
import g24.controller.observer.ScoreObserver;
import g24.controller.observer.TimeObserver;
import g24.controller.observer.VisitedRoomsObserver;
import g24.model.Model;

public class HUDModel extends Model implements VisitedRoomsObserver, IsaacHealthObserver, TimeObserver, ScoreObserver {
    private int currentHealth;
    private int maxHealth;
    private int visitedRooms;
    private int maxRooms;
    private int score;
    private int time;

    public HUDModel(int maxHealth, int maxRooms) {
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
        this.visitedRooms = 1;
        this.maxRooms = maxRooms;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getVisitedRooms() {
        return visitedRooms;
    }

    public int getMaxRooms() {
        return maxRooms;
    }

    public int getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }

    @Override
    public void updateVisitedRooms() {
        visitedRooms++;
    }

    @Override
    public void updateHealth(int health) {
        currentHealth += health;
        if(currentHealth > maxHealth) currentHealth = maxHealth;
        if(currentHealth < 0) currentHealth = 0;
    }

    @Override
    public void updateScore(int score) {
        this.score = score;
    }

    @Override
    public void updateTime(int time) {
        this.time = time;
    }
}
