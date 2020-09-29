package g24.controller.map;

import g24.controller.element.CollisionHandler;
import g24.controller.element.HealthController;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.model.map.RoomModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoomControllerTest {
    private RoomModel roomModel;
    private HealthController healthController;
    private ScoreController scoreController;
    private CollisionHandler collisionHandler;
    private boolean changeRoom;
    private boolean won;
    private DIRECTION changeRoomDirection;


    //@Before
    public void setup(){
        this.roomModel = mock(RoomModel.class);
        this.healthController = mock(HealthController.class);
        this.scoreController = mock(ScoreController.class);
        this.collisionHandler = mock(CollisionHandler.class);
        this.changeRoom = false;
        this.won = false;
        this.changeRoomDirection = DIRECTION.UP;

    }

    //@Test
    public void isInsideBoundaries(){


    }
}
