package hotciv.standard;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Unit;
import hotciv.framework.Position;
import hotciv.framework.UnitActionStrategy;

import java.util.HashMap;

public class GammaUnitActionStrategy implements UnitActionStrategy {
    public void performUnitActionAt(Position p, HashMap<Position, Unit> unitMap, Game game){
        Unit unit = unitMap.get(p);
        if(unit.getTypeString() == GameConstants.ARCHER){
            //Checking if unit is already fortified
            if(unit.getDefensiveStrength() == 3){
                unit.setDefensiveStrength(6);
            }
            else{
                unit.setDefensiveStrength(3);
            }
        }
        else if(unit.getTypeString() == GameConstants.SETTLER){
            //If there is no city at the space the settler is on, create one and remove the settler from the map
            if(game.getCityAt(p) == null){
                game.createCity(p);
                game.removeUnit(p);
            }
        }
    }
}
