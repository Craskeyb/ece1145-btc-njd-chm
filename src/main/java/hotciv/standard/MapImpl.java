package hotciv.standard;

import hotciv.framework.*;

import java.util.*;

public class MapImpl implements GameSetupStrategy{
    private HashMap<Position,Tile> tileMap = new HashMap<Position, Tile>();
    private HashMap<Position,Unit> unitMap = new HashMap<Position, Unit>();
    private HashMap<Position,City> cityMap = new HashMap<Position, City>();

    @Override
    public void setUpBoard() {
        /** Define the world as the DeltaCiv layout */
            // Basically we use a 'data driven' approach - code the
            // layout in a simple semi-visual representation, and
            // convert it to the actual Game representation.
            String[] layout =
                    new String[]{
                            "...ooMooooo.....",
                            "..ohhoooofffoo..",
                            ".oooooMooo...oo.",
                            ".ooMMMoooo..oooo",
                            "...ofooohhoooo..",
                            ".ofoofooooohhoo.",
                            "...ooo..........",
                            ".ooooo.ooohooM..",
                            ".ooooo.oohooof..",
                            "offfoooo.offoooo",
                            "oooooooo...ooooo",
                            ".ooMMMoooo......",
                            "..ooooooffoooo..",
                            "....ooooooooo...",
                            "..ooohhoo.......",
                            ".....ooooooooo..",
                    };
            // Conversion...
            String line;
            for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
                line = layout[r];
                for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                    char tileChar = line.charAt(c);
                    String type = "error";
                    if (tileChar == '.') {
                        type = GameConstants.OCEANS;
                    }
                    if (tileChar == 'o') {
                        type = GameConstants.PLAINS;
                    }
                    if (tileChar == 'M') {
                        type = GameConstants.MOUNTAINS;
                    }
                    if (tileChar == 'f') {
                        type = GameConstants.FOREST;
                    }
                    if (tileChar == 'h') {
                        type = GameConstants.HILLS;
                    }
                    Position p = new Position(r, c);
                    tileMap.put(p, new TileImpl(type));
                }
                for(int i=0;i<=GameConstants.WORLDSIZE;i++){
                    for(int j=0;j<=GameConstants.WORLDSIZE;j++){
                        if (i == 2 && j == 0){
                            unitMap.put(new Position(i,j),new UnitImpl(Player.RED, GameConstants.ARCHER));
                        }
                        else if (i == 3 && j == 2){
                            unitMap.put(new Position(i,j),new UnitImpl(Player.BLUE, GameConstants.LEGION));
                        }
                        else if (i == 4 && j == 3){
                            unitMap.put(new Position(i,j),new UnitImpl(Player.RED, GameConstants.SETTLER));
                        }
                        if (i == 8 && j == 12){
                            cityMap.put(new Position(i,j),new CityImpl(Player.RED));
                        }
                        else if (i == 4 && j == 5){
                            cityMap.put(new Position(i,j),new CityImpl(Player.BLUE));
                        }
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
}

