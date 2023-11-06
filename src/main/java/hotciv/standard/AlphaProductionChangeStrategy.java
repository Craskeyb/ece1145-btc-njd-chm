package hotciv.standard;
import hotciv.framework.*;

public class AlphaProductionChangeStrategy implements ProductionChangeStrategy {
    public void changeProduction(Game game, Position p, String unitType){
        if(unitType == GameConstants.ARCHER || unitType == GameConstants.SETTLER || unitType == GameConstants.LEGION){
            game.getCityAt(p).changeProduction(unitType);
        }
    }
}
