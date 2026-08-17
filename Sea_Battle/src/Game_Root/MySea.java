package Game_Root;

public class MySea implements Sea<PlayingMyCellLayout>{
    private final PlayingMyCellLayout[][] sea;
    public MySea(PlacementSea placementSea) {
        this.sea = new PlayingMyCellLayout[GameConfig.ROWS][GameConfig.COLS];
       createSea(placementSea);
    }
    private void createSea(PlacementSea placementSea) {
            for(int i = 0; i < GameConfig.COLS; i++){
                for(int j = 0; j < GameConfig.ROWS; j++){
                    if (placementSea.getCellType(j,i)== ShipPlacementCellLayout.EMPTY){
                        sea[j][i]=PlayingMyCellLayout.SEA;
                    } else {
                        sea[j][i]=PlayingMyCellLayout.LIVE;
                    }
                }
            }
    }

    public void attack(int x, int y){
      /*  if(sea[x][y]==PlayingMyCellLayout.SEA){
            sea[x][y]=PlayingMyCellLayout.MISS;
        } else if(sea[x][y]==PlayingMyCellLayout.LIVE) {
            sea[x][y]=PlayingMyCellLayout.HIT;
        }*/
    }


    public  PlayingMyCellLayout getCellType(int x, int y) {
        return sea[x][y];
    }

    public int[] shipCoordinates(int x, int y) {
        int startX = 0;
        int startY = 0;
        int[] coord = new int[2];
        Ship ship = getShip(x, y);
        if (ship.getOrientation() == Orientation.VERTICAL) {
            for (int i = -1; i >= startY-1; i--) {
                if (y+i>0) {
                    if (sea[x][y+i] == PlayingMyCellLayout.LIVE||sea[x][y+i] == PlayingMyCellLayout.HIT) {
                        startY--;
                    }
                }
            }
        } else if (ship.getOrientation() == Orientation.HORIZONTAL) {
            for (int i = -1; i >= startX-1; i--) {
                if (x+i>0) {
                    if (sea[x+i][y] == PlayingMyCellLayout.LIVE||sea[x+i][y] == PlayingMyCellLayout.HIT) {
                        startX--;
                    }
                }
            }
        }
        coord[0] = startX+x;
        coord[1] = startY+y;
        return coord;
    }

   public Ship getShip(int x, int y) {
        ShipType shipType = ShipType.PATROL;
        Ship ship = new Ship(shipType,Orientation.HORIZONTAL);
        int size = 0;
        if (x<GameConfig.ROWS&&(sea[x+1][y] == PlayingMyCellLayout.LIVE||
                sea[x+1][y] == PlayingMyCellLayout.HIT)) {
            size++;
            if (x<GameConfig.ROWS-1&&(sea[x+2][y] == PlayingMyCellLayout.LIVE||
                    sea[x+2][y] == PlayingMyCellLayout.HIT)) {
                size++;
                if (x<GameConfig.ROWS-2&&(sea[x+3][y] == PlayingMyCellLayout.LIVE||
                        sea[x+3][y] == PlayingMyCellLayout.HIT)) {
                    size++;
                }
            }
        }
        if (x>0&&(sea[x-1][y] == PlayingMyCellLayout.LIVE||
                sea[x-1][y] == PlayingMyCellLayout.HIT)) {
            size++;
            if (x>1&&(sea[x-2][y] == PlayingMyCellLayout.LIVE||
                    sea[x-2][y] == PlayingMyCellLayout.HIT)) {
                size++;
                if (x>2&&(sea[x-3][y] == PlayingMyCellLayout.LIVE||
                        sea[x-3][y] == PlayingMyCellLayout.HIT)) {
                    size++;
                }
            }
        }

        if (y<GameConfig.COLS&&(sea[x][y+1] == PlayingMyCellLayout.LIVE||
                sea[x][y+1] == PlayingMyCellLayout.HIT)) {
            ship.setOrientation(Orientation.VERTICAL);
            size++;
            if (y<GameConfig.COLS-1&&(sea[x][y+2] == PlayingMyCellLayout.LIVE||
                    sea[x][y+2] == PlayingMyCellLayout.HIT)) {
                size++;
                if (y<GameConfig.COLS-2&&(sea[x][y+3] == PlayingMyCellLayout.LIVE||
                        sea[x][y+3] == PlayingMyCellLayout.HIT)) {
                    size++;
                }
            }
        }
        if (y>0&&(sea[x][y-1] == PlayingMyCellLayout.LIVE||
                sea[x][y-1] == PlayingMyCellLayout.HIT)) {

            ship.setOrientation(Orientation.VERTICAL);
            size++;
            if (y>1&&(sea[x][y-2] == PlayingMyCellLayout.LIVE||
                    sea[x][y-2] == PlayingMyCellLayout.HIT)) {
                size++;
                if (y>2&&(sea[x][y-3] == PlayingMyCellLayout.LIVE||
                        sea[x][y-3] == PlayingMyCellLayout.HIT)) {
                    size++;
                }
            }
        }

        shipType = ShipType.values()[size];
        ship.setShipType(shipType);
        return ship;
    }

    public void setCellType(int x, int y, PlayingMyCellLayout cellType) {
        sea[x][y] = cellType;
    }
}
