package g24.model.map;

import g24.model.Model;
import g24.model.hud.HUDModel;

public class DungeonModel extends Model {
    private RoomModel roomModel;
    private HUDModel hudModel;

    public DungeonModel(RoomModel roomModel, HUDModel hudModel) {
        this.roomModel = roomModel;
        this.hudModel = hudModel;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public HUDModel getHudModel() {
        return hudModel;
    }
}
