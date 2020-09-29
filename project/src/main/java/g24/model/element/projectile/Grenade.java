package g24.model.element.projectile;

import g24.GameConfig;
import g24.model.utils.Symbol;

public class Grenade extends Projectile {
    public static final int damage = 5;
    public static final int velocity = GameConfig.FPS/20;
    public static final Symbol symbol = new Symbol("(|)","#d2afab");

    public Grenade(int x, int y) {
        super(x, y, symbol, damage,velocity);
    }
}
