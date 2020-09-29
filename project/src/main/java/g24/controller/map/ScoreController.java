package g24.controller.map;

import g24.controller.observer.Observable;
import g24.controller.observer.ScoreObserver;

import java.util.ArrayList;
import java.util.List;

public class ScoreController implements Observable<ScoreObserver> {
    int score;
    private List<ScoreObserver> scoreObserverList;

    public ScoreController() {
        score = 0;
        scoreObserverList = new ArrayList<>();
    }

    public void increaseScore(int delta) {
        score += delta;
        if(score > 999) score = 999;

        notifyObservers();
    }

    public void decreaseScore(int delta) {
        score -= delta;
        if(score < 0) score = 0;

        notifyObservers();
    }

    @Override
    public void addObserver(ScoreObserver observer) {
        scoreObserverList.add(observer);
    }

    @Override
    public void removeObserver(ScoreObserver observer) {
        scoreObserverList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(ScoreObserver observer : scoreObserverList)
            observer.updateScore(score);
    }
}
