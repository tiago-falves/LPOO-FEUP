package g24.controller.map;

import g24.model.map.MapTemplate;
import g24.model.map.RoomType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MapGenerator {
    private final static int width = 6;
    private final static int height = 6;
    private final static int roomVariation = 0;
    private final static int roomMinimum = 6;
    private List<RoomType> intermediateRooms;
    private MapTemplate grid;
    int numRooms;

    public MapGenerator() {
        Random random = new Random();
        this.numRooms = random.nextInt(roomVariation + 1) + roomMinimum;
        this.grid = new MapTemplate(width, height);

        this.intermediateRooms = new LinkedList<>(
                Arrays.asList(
                    RoomType.ENEMY_EASY,
                    RoomType.ENEMY_MEDIUM,
                    RoomType.ENEMY_HARD,
                    RoomType.ENEMY_EASY,
                    RoomType.TRAP,
                    RoomType.TRAP
                )
        );
    }

    public MapTemplate generateGrid() {
        Random rand = new Random();

        int startingX = width/2;
        int startingY = height/2;
        grid.setRoom(startingX, startingY, RoomType.START);
        int roomsGenerated = 1;

        while(roomsGenerated < numRooms) {
            int randomX = rand.nextInt(width);
            int randomY = rand.nextInt(height);
            RoomType currentRoom = grid.getRoom(randomX, randomY);

            if(currentRoom == RoomType.EMPTY && roomHasNeighbour(randomX, randomY)) {

                int generatedIndex = rand.nextInt(intermediateRooms.size());
                RoomType generatedRoom = intermediateRooms.get(generatedIndex);
                intermediateRooms.remove(generatedIndex);

                grid.setRoom(randomX, randomY, generatedRoom);
                roomsGenerated++;
            }
        }
        
        int deadendX = startingX;
        int deadendY = startingY;
        double maxDist = 0;
        for(int r = 0; r < height; r++) {
            for(int c = 0; c < width; c++) {
                double currentDist = Math.sqrt(Math.pow(c-startingX, 2) + Math.pow(r-startingY, 2));

                if(grid.getRoom(c, r) != RoomType.EMPTY && grid.getRoom(c, r) != RoomType.START && currentDist > maxDist) {
                    maxDist = currentDist;
                    deadendX = c;
                    deadendY = r;
                }
            }
        }
        grid.setRoom(deadendX, deadendY, RoomType.BOSS);

        return grid;
    }

    private int numberOfNeighbours(int x, int y) {
        int number = 0;

        RoomType up = (y > 0 ? grid.getRoom(x, y-1) : RoomType.EMPTY);
        if(up != RoomType.EMPTY) number++;

        RoomType down = (y < height-1 ? grid.getRoom(x, y+1) : RoomType.EMPTY);
        if(down != RoomType.EMPTY) number++;

        RoomType left = (x > 0 ? grid.getRoom(x-1, y) : RoomType.EMPTY);
        if(left != RoomType.EMPTY) number++;

        RoomType right = (x < width-1 ? grid.getRoom(x+1, y) : RoomType.EMPTY);
        if(right != RoomType.EMPTY) number++;

        return number;
    }

    private boolean roomHasNeighbour(int x, int y) {
        return numberOfNeighbours(x, y) != 0;
    }

}
