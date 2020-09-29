package g24.controller.element;

import g24.controller.map.RoomController;
import g24.controller.observer.IsaacHealthObserver;
import g24.controller.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class HealthController implements Observable<IsaacHealthObserver> {
    private List<IsaacHealthObserver> observers;
    private int healthVariation;

    public HealthController() {
        this.observers = new ArrayList<>();
        healthVariation = 0;
    }

    public void increaseHealth(int amount) {
        healthVariation = amount;
        notifyObservers();
    }

    public void decreaseHealth(int amount) {
        healthVariation = -amount;
        notifyObservers();
    }

    @Override
    public void addObserver(IsaacHealthObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IsaacHealthObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IsaacHealthObserver observer : observers)
            observer.updateHealth(healthVariation);
    }
}
