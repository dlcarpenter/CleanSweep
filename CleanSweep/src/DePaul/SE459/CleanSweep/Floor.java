import java.util.HashMap;
import java.util.Map;

class Floor {
    private Map<Integer, Tile> tiles;

    public Floor()
    {
        tiles = new HashMap<>();
    }    
    
    public Tile getTile(int x, int y) {
        Coordinate c = new Coordinate(x,y);
        return tiles.get(c.hashCode());
    }

    public void addTile(Tile t) {
        tiles.put(t.getCoordinateHashCode(), t);
    }
}
