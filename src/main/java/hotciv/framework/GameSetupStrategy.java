package hotciv.framework;

import hotciv.standard.MapImpl;

import java.util.HashMap;

public interface GameSetupStrategy {
    void setUpBoard();
    HashMap<Position,City> getCityMap();
    HashMap<Position,Tile> getTileMap();
    HashMap<Position,Unit> getUnitMap();

}
