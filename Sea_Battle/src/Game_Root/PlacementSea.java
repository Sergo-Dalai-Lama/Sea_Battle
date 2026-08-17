package Game_Root;

import java.util.EnumMap;
import java.util.Map;

public class PlacementSea {
    private final ShipPlacementCellLayout[][] sea;
    private final GameConfig gameConfig;
    private final Map<ShipType, Integer> myShipConfig;//сколько кораблей каждого типа еще можно поставить
    public PlacementSea(GameConfig gameConfig) {
        this.sea = new ShipPlacementCellLayout[GameConfig.ROWS][GameConfig.COLS];
        this.gameConfig = gameConfig;
        this.myShipConfig = new EnumMap<>(ShipType.class);
        for (ShipType shipType : ShipType.values()) {
            int count = gameConfig.getShipConfig(shipType).getDefaultCount();
            myShipConfig.put(shipType, count);//создали счетчик для конкретного поля
        }
        createSea();
    }

    private void createSea(){
        for (int i = 0; i < GameConfig.ROWS; i++) {
            for (int j = 0; j < GameConfig.COLS; j++) {
                sea[i][j] = ShipPlacementCellLayout.EMPTY;
            }
        }
    }

    public ShipPlacementCellLayout getCellType(int x, int y) {
        return sea[x][y];
    }

    public void setShip(Ship ship, int x, int y) {
        if ((canSetShip(ship, x, y))) {
            if (!shipCollision(ship, x, y)) {
                myShipConfig.put(ship.getShipType(), myShipConfig.get(ship.getShipType())-1);
                for (int i = 0; i<gameConfig.getShipConfig(ship.getShipType()).getSize(); i++) {//слишком длинный кусок кода, надо всё-таки создавать массив кораблей
                        if (ship.getOrientation() == Orientation.HORIZONTAL) {
                            sea[x+i][y] =  ShipPlacementCellLayout.DECK;
                        }
                        if (ship.getOrientation() == Orientation.VERTICAL) {
                            sea[x][y+i] = ShipPlacementCellLayout.DECK;
                        }
                }
            } else {
                System.out.println("2.How many more ships do you need?!");
            }
        } else {
            System.out.println("1.How many more ships do you need?!");

        }
    }

    private boolean canSetShip(Ship ship, int x, int y) {

        if (((ship.getOrientation() == Orientation.HORIZONTAL)&&//разделить на два отдельных метода
                (x+ship.getShipType().getSize()<=GameConfig.ROWS))
                ||((ship.getOrientation() == Orientation.VERTICAL)&&
                (y+ship.getShipType().getSize()<=GameConfig.COLS))) {
            if (myShipConfig.getOrDefault(ship.getShipType(), 0)>0) {
                return true;

            }
        }
        return false;

    }

    private boolean shipCollision(Ship ship, int x, int y) {
        boolean collision = false;
        for (int i = -1; i<ship.getShipType().getSize()+1; i++) {
            for (int j = -1; j<1; j++) {
                    if (ship.getOrientation() == Orientation.HORIZONTAL) {
                        if (x+i>=0&&y+j>=0&&x+ship.getShipType().getSize()<GameConfig.ROWS&&y+1<GameConfig.COLS) {
                            if (sea[x + i][y + j] == ShipPlacementCellLayout.DECK) {
                                collision = true;
                                return collision;
                            }
                        }
                    }
                    if (ship.getOrientation() == Orientation.VERTICAL) {
                        if (x+j>=0&&y+i>=0&&x+1<GameConfig.ROWS&&y+ship.getShipType().getSize()<GameConfig.COLS) {
                            if (sea[x + j][y + i] == ShipPlacementCellLayout.DECK) {
                                collision = true;
                                return collision;
                            }
                        }
                    }
                }

        }
        return collision;
    }

}
