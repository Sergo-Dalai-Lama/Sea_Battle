package Game_Root;

interface Sea <T>{
    void attack(int x, int y);
    T getCellType(int x, int y);//дженерик
    Ship getShip(int x, int y);
    void setCellType(int x, int y,T cellType);
}
