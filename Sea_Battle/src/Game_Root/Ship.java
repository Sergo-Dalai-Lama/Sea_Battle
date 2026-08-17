package Game_Root;

public class Ship {//класс создания корабля
    //класс должен иметь тип корабля, его ориентацию,
    // и как-то надо сделать так, чтобы сохранялось количество
    //попаданий в корабль.
    private int xBase;
    private int yBase;
    private ShipType shipType;
    private Orientation orientation;
    public  Ship(ShipType shipType, Orientation orientation, int xBase, int yBase) {
        this.shipType = shipType;
        this.orientation = orientation;
        this.xBase = xBase;
        this.yBase = yBase;
    }
    public ShipType getShipType() {
        return shipType;
    }
    public void setShipType(ShipType newShipType) {
        this.shipType = newShipType;
    }
    public Orientation getOrientation() {
        return orientation;
    }
    public void setOrientation(Orientation newOrientation) {
        this.orientation = newOrientation;
    }
    public int getxBase() {
        return xBase;
    }
    public int getyBase() {
        return yBase;
    }
    public void setxBase(int xBase) {
        this.xBase = xBase;
    }
    public void  setyBase(int yBase) {
        this.yBase = yBase;
    }
}
