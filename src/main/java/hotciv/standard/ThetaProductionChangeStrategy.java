package hotciv.standard;
import hotciv.framework.*;

public class ThetaProductionChangeStrategy implements ProductionChangeStrategy {
    //Allows for UFO as an input
    public void changeProduction(Game game, Position p, String unitType){
            game.getCityAt(p).changeProduction(unitType);
    }
}
