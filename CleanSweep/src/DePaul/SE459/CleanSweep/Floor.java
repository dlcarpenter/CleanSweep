import java.util.LinkedList;

class Floor {
    private LinkedList<LinkedList<Tile>> tiles;

    public Floor()
    {        
    }    
    
    public Tile getTile(int x, int y) {
        return tiles.get(x).get(y);
    }

    public void addTile(Tile t) {
        t = this.tiles.get(t.getX()).get(t.getY());
    }
}
