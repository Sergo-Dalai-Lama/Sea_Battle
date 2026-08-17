package Game_Root;

import java.util.List;

public class EnemySea implements Sea<PlayingEnemyCellLayout> {
    private final PlayingEnemyCellLayout[][] sea;
    private final Ship[][] grid;//надо создать массив кораблей и хранить в каждой
    // клетке одного корабля ссылки на один и тот же корабль
    //это позволит убрать значительную часть кода с перебором клеток и вычислением размера корабля
    //скорее всего надо будет отдельно переписать код игры, потому что этот теперь не работает и будет сложнее проводить
    //его отладку.
    //Надо научиться вносить изменения так, чтобы это не ломало старый код.
    //При работе с кодом и запущенной игрой на сервере нельзя будет позволить себе ломать игру
    //плюс такой подход поможет проще находить ошибки в работе кода
    public MySea enemySea;
    public EnemySea(MySea enemySea) {
        this.sea = new PlayingEnemyCellLayout[GameConfig.ROWS][GameConfig.COLS];
        this.enemySea = enemySea;
        this.grid = new Ship[GameConfig.ROWS][GameConfig.COLS];
        createSea();
    }

    private void createSea(){
        for (int i = 0; i < GameConfig.ROWS; i++) {
            for (int j = 0; j < GameConfig.COLS; j++) {
                sea[i][j] = PlayingEnemyCellLayout.UNK;
            }
        }
    }

    public void applyPlacement(List<ShipType> shipTypes){
        for (int i = 0; i < GameConfig.ROWS; i++) {
            for (int j = 0; j < GameConfig.COLS; j++) {}
        }
    }

    public void attack(int x, int y){
        if (enemySea.getCellType(x, y) == PlayingMyCellLayout.LIVE){

            setCellType(x,y,PlayingEnemyCellLayout.HIT);
            enemySea.setCellType(x,y,PlayingMyCellLayout.HIT);
            if (killShip(x, y)){//если корабль убит - закрасить всё вокруг, организовать отдельный метод
                int baseX = enemySea.shipCoordinates(x, y)[0];
                int baseY = enemySea.shipCoordinates(x, y)[1];
                for (int i = 0; i <enemySea.getShip(x, y).getShipType().getSize()+2; i++) {
                    if (enemySea.getShip(x, y).getOrientation()==Orientation.HORIZONTAL) {
                        if (baseX>0&&baseX<GameConfig.ROWS){
                            if (baseY>0){
                                         setCellType(baseX+i-1,baseY-1,PlayingEnemyCellLayout.MISS);
                                enemySea.setCellType(baseX+i-1,baseY-1,PlayingMyCellLayout.MISS);
                            }
                            if (baseY==y&&sea[baseX+i-1][baseY]!= PlayingEnemyCellLayout.HIT){
                                         setCellType(baseX+i-1,baseY,PlayingEnemyCellLayout.MISS);
                                enemySea.setCellType(baseX+i-1,baseY,PlayingMyCellLayout.MISS);
                            }
                            if (baseY+i<GameConfig.ROWS-1){
                                         setCellType(baseX+1-i,baseY+1,PlayingEnemyCellLayout.MISS);
                                enemySea.setCellType(baseX+1-i,baseY+1,PlayingMyCellLayout.MISS);

                            }
                        }
                    }

                    if (enemySea.getShip(x, y).getOrientation()==Orientation.VERTICAL) {
                        if (baseY>0&&baseY<GameConfig.COLS){
                            if (baseX>0){
                                         setCellType(baseX-1,baseY+i-1,PlayingEnemyCellLayout.MISS);
                                enemySea.setCellType(baseX-1,baseY+i-1,PlayingMyCellLayout.MISS);
                            }
                            if (baseX==x&&sea[baseX][baseY+i-1]!= PlayingEnemyCellLayout.HIT){
                                         setCellType(baseX,baseY+i-1,PlayingEnemyCellLayout.MISS);
                                enemySea.setCellType(baseX,baseY+i-1,PlayingMyCellLayout.MISS);
                            }
                            if (baseX+i<GameConfig.COLS-1){
                                         setCellType(baseX+1,baseY+i-1,PlayingEnemyCellLayout.MISS);
                                enemySea.setCellType(baseX+1,baseY+i-1,PlayingMyCellLayout.MISS);
                            }
                        }
                    }
                }
            }

        }
        else{
                     setCellType(x,y,PlayingEnemyCellLayout.MISS);
            enemySea.setCellType(x,y,PlayingMyCellLayout.MISS);
        }
    }

    public PlayingEnemyCellLayout getCellType(int x, int y){
        return sea[x][y];
    }

    private boolean killShip(int x, int y){
        return getShip(x, y).getShipType() == enemySea.getShip(x, y).getShipType();
    }

    public Ship getShip(int x, int y) {//можно будет вынести метод
      ShipType shipType = ShipType.PATROL;
      Ship ship = new Ship(shipType,Orientation.HORIZONTAL,x,y);
      int size = 0;
      if (x<GameConfig.ROWS&&sea[x+1][y] == PlayingEnemyCellLayout.HIT) {
          size++;
          if (x<GameConfig.ROWS-1&&sea[x+2][y] == PlayingEnemyCellLayout.HIT) {
              size++;
              if (x<GameConfig.ROWS-2&&sea[x+3][y] == PlayingEnemyCellLayout.HIT) {
                  size++;
              }
          }
      }
      if (x>0&&sea[x-1][y] == PlayingEnemyCellLayout.HIT) {
          size++;
          if (x>1&&sea[x-2][y] == PlayingEnemyCellLayout.HIT) {
              size++;
              if (x>2&&sea[x-3][y] == PlayingEnemyCellLayout.HIT) {
                  size++;
              }
          }
      }

      if (y<GameConfig.COLS&&sea[x][y+1] == PlayingEnemyCellLayout.HIT) {
          ship.setOrientation(Orientation.VERTICAL);
          size++;
          if (y<GameConfig.COLS-1&&sea[x][y+2] == PlayingEnemyCellLayout.HIT) {
              size++;
              if (y<GameConfig.COLS-2&&sea[x][y+3] == PlayingEnemyCellLayout.HIT) {
                  size++;
              }
          }
      }
      if (y>0&&sea[x][y-1] == PlayingEnemyCellLayout.HIT) {
          ship.setOrientation(Orientation.VERTICAL);
          size++;
          if (y>1&&sea[x][y-2] == PlayingEnemyCellLayout.HIT) {
              size++;
              if (y>2&&sea[x][y-3] == PlayingEnemyCellLayout.HIT) {
                  size++;
              }
          }
      }

      shipType = ShipType.values()[size];
      ship.setShipType(shipType);
      return ship;
  }

  public void setCellType(int x, int y,PlayingEnemyCellLayout  cellType) {
      sea[x][y]= cellType;
  }

}
