package g24.view;

import g24.model.Model;

public abstract class View<M extends Model> {

    public abstract void draw(M model);
}