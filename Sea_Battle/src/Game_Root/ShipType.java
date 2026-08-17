package Game_Root;

public enum ShipType {
    PATROL(1,4,10),
    DESTROYER(2,3,20),
    CRUISER(3,2,30),
    BATTLESHIP(4,1,40);

    private final int size;

    ShipType(int size, int count, int score) {
        this.size = size;

    }

    public int getSize() {
        return size;
    }

}
