package g24.view.lanterna.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import g24.model.element.Element;
import g24.model.element.monsters.GunMonster;
import g24.model.hud.HUDModel;
import g24.model.utils.Position;
import g24.model.element.projectile.Projectile;
import g24.model.utils.Symbol;
import g24.model.map.*;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.view.lanterna.LanternaAction;
import g24.view.lanterna.LanternaStateView;

import java.io.IOException;
import java.util.List;

public class LanternaPlayView extends LanternaStateView<DungeonModel> {

    private static final String backgroundColor = "#593521";
    private LanternaHUDView hudView;

    public LanternaPlayView(TerminalScreen screen, LanternaAction action, LanternaHUDView hudView) {
        super(screen, action);
        this.hudView = hudView;
    }

    @Override
    public void draw(DungeonModel dungeonModel){
        screen.clear();

        HUDModel hudModel = dungeonModel.getHudModel();
        hudView.draw(hudModel);

        RoomModel roomModel = dungeonModel.getRoomModel();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.fillRectangle(new TerminalPosition(0, 1),new TerminalSize(roomModel.getWidth(), roomModel.getHeight()),' ');

        for (Element element : roomModel.getAllElements()){
            drawCharacter(element.getPositions().getFirstPosition(), element.getSymbol(), backgroundColor);
        }
        drawBullets(roomModel,backgroundColor);

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void drawCharacter(Position position, Symbol symbol, String backgroundColor) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.setForegroundColor(TextColor.Factory.fromString(symbol.getColor()));
        graphics.putString(position.getX(), position.getY() + 1, symbol.getSymbol());

    }

    private void drawBullets(RoomModel roomModel,String backgroundColor){
        List<Projectile> projectiles = roomModel.getIsaac().getGun().getProjectiles();
        for(Projectile projectile : projectiles){
            if (projectile.exists()){
                drawCharacter(projectile.getPositions().getFirstPosition(),projectile.getSymbol(),backgroundColor);
            }
        }
        for (GunMonster monster : roomModel.getGunMonsters()){
            for(Projectile projectile : monster.getGun().getProjectiles()){
                if (projectile.exists()){
                    drawCharacter(projectile.getPositions().getFirstPosition(),projectile.getSymbol(),backgroundColor);
                }
            }
        }


    }

}
