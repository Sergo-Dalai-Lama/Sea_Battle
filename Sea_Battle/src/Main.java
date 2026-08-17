import Game_Root.*;

void main() {

    PlacementSea placementSea1 = new PlacementSea(GameConfig.defaultConfig());//установка кораблей 1
    Ship ship1 = new Ship(ShipType.BATTLESHIP,Orientation.HORIZONTAL);
    Ship ship2 = new Ship(ShipType.CRUISER,Orientation.VERTICAL);
    Ship ship5 = new Ship(ShipType.DESTROYER,Orientation.VERTICAL);
    placementSea1.setShip(ship1,3,3);//корабль на клетках (3,3)(4,3)(5,3)(6,3)
    placementSea1.setShip(ship2,0,0);//корабль на клетке (0,0)(0,1)(0,2)

    MySea mySea1 = new MySea(placementSea1);
    PlacementSea placementSea2 = new PlacementSea(GameConfig.defaultConfig());//установка кораблей 2
    placementSea2.setShip(ship5,1,1);
    placementSea2.setShip(ship1,4,3);
    MySea mySea2 = new MySea(placementSea2);

    System.out.println();

    EnemySea enemySea1 = new EnemySea(mySea2);
    EnemySea enemySea2 = new EnemySea(mySea1);

    enemySea1.attack(1,1);
    enemySea1.attack(1,2);
    enemySea1.attack(4,3);
    enemySea1.attack(4,4);
    System.out.println("My Sea 1");

    for (int i = 0;i<GameConfig.COLS;i++) {//мое игровое поле
        System.out.println();
        for (int j = 0;j<GameConfig.ROWS;j++) {
            System.out.printf("%-6s", mySea1.getCellType(j,i));
        }
    }

    System.out.println();
    System.out.println("Enemy Sea 1");
    for (int i = 0;i<GameConfig.COLS;i++) {//я вижу игровое поле противника
        System.out.println();
        for (int j = 0;j<GameConfig.ROWS;j++) {
            System.out.printf("%-6s", enemySea1.getCellType(j,i));
        }
    }

    System.out.println();
    System.out.println("My Sea 2");
    for (int i = 0;i<GameConfig.COLS;i++) {//так видит противник свое поле
        System.out.println();
        for (int j = 0;j<GameConfig.ROWS;j++) {
            System.out.printf("%-6s", mySea2.getCellType(j,i));
        }
    }

    System.out.println();
    System.out.println("Enemy Sea 2");
    for (int i = 0;i<GameConfig.COLS;i++) {//так видит мое игровое поле противник
        System.out.println();
        for (int j = 0;j<GameConfig.ROWS;j++) {
            System.out.printf("%-6s", enemySea2.getCellType(j,i));
        }
    }

}
