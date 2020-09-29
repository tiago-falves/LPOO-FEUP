package g24.view;

import g24.controller.commands.user.COMMAND;
import g24.model.Model;

public abstract class StateView<M extends Model> extends View<M>{
    public abstract COMMAND getCommand();
    public abstract void exit();
}
