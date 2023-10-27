package hotciv.standard;

import hotciv.framework.AbstractFactory;
import hotciv.framework.AgingStrategy;
import hotciv.framework.GameSetupStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.framework.UnitActionStrategy;

public class AlphaCivFactory implements AbstractFactory {

    @Override
    public AgingStrategy createAgingStrategy() {
        return null;
    }

    @Override
    public GameSetupStrategy createGameSetup() {
        return null;
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return null;
    }
    
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return null;
    }
    
}
