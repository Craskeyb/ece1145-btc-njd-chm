package hotciv.standard;

import hotciv.framework.AbstractFactory;
import hotciv.framework.AgingStrategy;
import hotciv.framework.GameSetupStrategy;
import hotciv.framework.WinningStrategy;

public class DeltaCivFactory implements AbstractFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return null;
    }

    @Override
    public GameSetupStrategy createGameSetup() {
        return new DeltaMapImpl();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return null;
    }
     /*
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return null;
    }
    */
}
