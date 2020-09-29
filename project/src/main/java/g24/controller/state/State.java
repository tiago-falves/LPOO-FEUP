package g24.controller.state;

import g24.controller.GameController;
import g24.model.Model;
import g24.view.StateView;

public abstract class State<M extends Model> {
    public abstract void update(GameController game);
    public abstract M getStateModel();
    public abstract StateView<M> getStateView();
    public abstract StateController getStateController();
}
