package hotciv.standard;

import hotciv.framework.AbstractFactory;
import hotciv.framework.AgingStrategy;
import hotciv.framework.GameSetupStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.AttackStrategy;

public class AlphaCivFactory implements AbstractFactory {

    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public GameSetupStrategy createGameSetup() {
        return new MapImpl();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new AlphaWinningStrategy();
    }
    
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }
    
    @Override
    public AttackStrategy createAttackStrategy() {
        return new AlphaAttackStrategy();
    }
}
