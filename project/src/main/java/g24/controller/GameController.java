package g24.controller;

import g24.GameConfig;

import g24.controller.state.MenuState;
import g24.controller.state.State;
import g24.view.ViewFactory;

import java.io.IOException;

public class GameController {
    private State state;
    private ViewFactory viewFactory;
    private boolean isRunning;

    public GameController(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
        state = new MenuState(this);
        isRunning = true;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void start() {
        state.getStateView().draw(state.getStateModel());

        while (isRunning && !state.getStateController().hasEnded()) {
            try {
                Thread.sleep(1000 / GameConfig.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            state.update(this);
        }

    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void shutdown() {
        isRunning = false;
    }
}
