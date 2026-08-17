package Game_Root;

import java.util.EnumMap;
import java.util.Map;

public final class GameConfig {
    public static final int ROWS = 10;
    public static final int COLS = 10;

    //ЗДЕСЬ НАСТРОЙКА ЭНУМАП ДЛЯ КОРАБЛИКОВ
    private final Map<ShipType,ShipConfig> shipConfigs;//установка мапы конфига кораблей - тип корабля - конфиг корабля
    public GameConfig(Map<ShipType,ShipConfig> shipConfigs) {//передаем в конфиг игры мапу с кораблями
        this.shipConfigs = Map.copyOf(shipConfigs);//не даем меняться мапе кораблей, делаем это в каждом классе отдельно
    }

    public static GameConfig defaultConfig() {//создаем мапу энума - фабричный метод
        Map<ShipType,ShipConfig> shipConfigs = new EnumMap<>(ShipType.class);
        shipConfigs.put(ShipType.BATTLESHIP,new ShipConfig(4,1,40));
        shipConfigs.put(ShipType.CRUISER,new ShipConfig(3,2,30));
        shipConfigs.put(ShipType.DESTROYER,new ShipConfig(2,3,20));
        shipConfigs.put(ShipType.PATROL,new ShipConfig(1,4,10));
        return new GameConfig(shipConfigs);//ВОЗВРАЩАЕМ ПРИ СОЗДАНИИ ПОЛЯ НОВУЮ КОПИЮ
    }

    public  ShipConfig getShipConfig(ShipType shipType) {
        ShipConfig shipConfig = shipConfigs.get(shipType);
        if (shipConfig == null) {
            throw new IllegalArgumentException("Unknown ship type: " + shipType);
        }
        return shipConfig;
    }
}
