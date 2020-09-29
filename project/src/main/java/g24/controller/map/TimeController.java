package g24.controller.map;

import g24.controller.observer.Observable;
import g24.controller.observer.TimeObserver;

import java.util.ArrayList;
import java.util.List;

public class TimeController implements Observable<TimeObserver>  {
    private int time;
    private int start;
    private List<TimeObserver> timeObserverList;

    public TimeController() {
        time = 0;
        start = (int) (System.currentTimeMillis() / 1000);
        timeObserverList = new ArrayList<>();
    }

    public void updateTime() {
        int elapsed = (int) (System.currentTimeMillis() / 1000) - start;

        if(elapsed > time) {
            time = elapsed;
            notifyObservers();
        }
    }

    @Override
    public void addObserver(TimeObserver observer) {
        timeObserverList.add(observer);
    }

    @Override
    public void removeObserver(TimeObserver observer) {
        timeObserverList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(TimeObserver observer : timeObserverList)
            observer.updateTime(time);
    }
}
