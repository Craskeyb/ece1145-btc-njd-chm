package hotciv.standard;

import hotciv.framework.*;

public class DeltaCivFactory implements AbstractFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public GameSetupStrategy createGameSetup() {
        return new DeltaMapImpl();
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

    @Override
    public WorkforceStrategy createWorkforceStrategy(){
        return new AlphaWorkforceStrategy();
    }

    @Override 
    public ProductionChangeStrategy createProductionChangeStrategy(){
        return new AlphaProductionChangeStrategy();
    }
}
