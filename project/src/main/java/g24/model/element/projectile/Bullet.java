package g24.model.element.projectile;

import g24.GameConfig;
import g24.model.utils.Symbol;

public class Bullet extends Projectile {
    public static final int damage = 1;
    public static final int velocity = GameConfig.FPS/1000;
    public static final Symbol symbol = new Symbol("*","#d2afab");

    public Bullet(int x, int y) {
        super(x, y, symbol, damage,velocity);
    }
}
