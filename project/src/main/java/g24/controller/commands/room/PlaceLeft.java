package g24.controller.commands.room;

import g24.controller.map.RoomController;
import g24.model.element.Isaac;
import g24.model.utils.Position;
import g24.model.utils.Positions;

public class PlaceLeft extends PlacementCommand{
    @Override
    public void execute(RoomController roomController, Isaac isaac) {
        Positions newIsaacPositions = new Positions();
        int isaacSize = isaac.getSymbol().getSize();

        for (int i = 0; i < isaacSize; i++) {
            newIsaacPositions.addPosition(
                    new Position(
                            3 + i,
                            roomController.getRoomModel().getHeight() / 2
                    )
            );
        }

        isaac.setPositions(newIsaacPositions);
    }
}
