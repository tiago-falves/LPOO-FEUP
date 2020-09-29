package g24.controller.hud;

import g24.controller.map.MapController;
import g24.model.hud.HUDModel;

public class HUDController {
    private HUDModel hudModel;

    public HUDController(MapController mapController, HUDModel hudModel) {
        this.hudModel = hudModel;
        mapController.addObserver(hudModel);
        mapController.getHealthController().addObserver(hudModel);
        mapController.getTimeController().addObserver(hudModel);
        mapController.getScoreController().addObserver(hudModel);
    }

    public HUDModel getHudModel() {
        return hudModel;
    }

}
