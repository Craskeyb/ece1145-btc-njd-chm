package hotciv.standard;

import hotciv.framework.*;

import java.util.*;

public class MapImpl implements GameSetupStrategy {
    private HashMap<Position,Tile> tileMap = new HashMap<Position, Tile>();
    private HashMap<Position,Unit> unitMap = new HashMap<Position, Unit>();
    private HashMap<Position,City> cityMap = new HashMap<Position, City>();
    private HashMap<String,Integer> unitCosts = new HashMap<String,Integer>();

    public void setUpBoard() {

        unitCosts.put(GameConstants.ARCHER,10);
        unitCosts.put(GameConstants.LEGION,15);
        unitCosts.put(GameConstants.SETTLER,30);

        for (int i = 0; i <= GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j <= GameConstants.WORLDSIZE; j++) {
                if (i == 1 && j == 0) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.OCEANS));
                } else if (i == 0 && j == 1) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.HILLS));
                } else if (i == 2 && j == 2) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.MOUNTAINS));
                } else {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
                }
                if (i == 2 && j == 0) {
                    unitMap.put(new Position(i, j), new UnitImpl(Player.RED, GameConstants.ARCHER));
                } else if (i == 3 && j == 2) {
                    unitMap.put(new Position(i, j), new UnitImpl(Player.BLUE, GameConstants.LEGION));
                } else if (i == 4 && j == 3) {
                    unitMap.put(new Position(i, j), new UnitImpl(Player.RED, GameConstants.SETTLER));
                }
                if (i == 1 && j == 1) {
                    cityMap.put(new Position(i, j), new CityImpl(Player.RED));
                } else if (i == 4 && j == 1) {
                    cityMap.put(new Position(i, j), new CityImpl(Player.BLUE));
                }
            }
        }
    }



    @Override
    public HashMap<Position, City> getCityMap() {
        return cityMap;
    }

    @Override
    public HashMap<Position, Tile> getTileMap() {
        return tileMap;
    }

    @Override
    public HashMap<Position, Unit> getUnitMap(){
        return unitMap;
    }

    @Override
    public HashMap<String,Integer> getUnitCosts(){
        return unitCosts;
    }
}

