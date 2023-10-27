package hotciv.standard;

import hotciv.framework.AbstractFactory;
import hotciv.framework.AgingStrategy;
import hotciv.framework.GameSetupStrategy;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WinningStrategy;

public class GammaCivFactory implements AbstractFactory{
    @Override
    public AgingStrategy createAgingStrategy() {return new BetaAgingStrategy();}

    @Override
    public GameSetupStrategy createGameSetup() {
        return new MapImpl();
    }

    @Override
    public WinningStrategy createWinningStrategy() {return new WinningStrategy();}
     
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaCivUnitActionStrategy();
    }
    
}
