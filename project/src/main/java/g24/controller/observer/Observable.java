package g24.controller.observer;

public interface Observable<O> {
    void addObserver(O observer);
    void removeObserver(O observer);
    void notifyObservers();
}
