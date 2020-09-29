package g24.model.map;

import g24.model.Model;
import g24.model.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class MapTemplate extends Model {
    private int width;
    private int height;
    private Position start;
    private List<List<RoomType>> rooms;

    public MapTemplate(int width, int height) {
        this.width = width;
        this.height = height;
        this.start = new Position(width/2, height/2);
        this.rooms = new ArrayList<>();

        initializeRooms();
    }

    private void initializeRooms() {
        for(int i = 0; i < height; i++) {

            List<RoomType> lineOfRooms = new ArrayList<>();
            for(int j = 0; j < width; j++) {
                lineOfRooms.add(RoomType.EMPTY);
            }
            rooms.add(lineOfRooms);
        }
    }

    public RoomType getRoom(int x, int y) {
        return rooms.get(y).get(x);
    }

    public void setRoom(int x, int y, RoomType type) {
        rooms.get(y).set(x, type);
    }

    public List<List<RoomType>> getRooms() {
        return rooms;
    }

    public Position getStart() {
        return start;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
