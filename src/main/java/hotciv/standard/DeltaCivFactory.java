package hotciv.standard;

import hotciv.framework.*;

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
    
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }
    
    @Override
    public AttackStrategy createAttackStrategy() {
        return new AlphaAttackStrategy();
    }

    @Override
    public WorkforceStrategy createWorkforceStrategy(){return new AlphaWorkforceStrategy();}
}
