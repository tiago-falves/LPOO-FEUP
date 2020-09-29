package g24.view;

import g24.model.map.DungeonModel;
import g24.model.menu.GameOverModel;
import g24.model.menu.GameWonModel;
import g24.model.menu.MenuModel;

public interface ViewFactory {
    StateView<MenuModel> createMenuStateView();
    StateView<DungeonModel> createPlayStateView();
    StateView<GameWonModel> createGameWonStateView();
    StateView<GameOverModel> createGameOverStateView();
}
