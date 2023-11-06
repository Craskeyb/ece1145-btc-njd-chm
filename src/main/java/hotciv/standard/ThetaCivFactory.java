package hotciv.standard;

import hotciv.framework.*;

public class ThetaCivFactory implements AbstractFactory {

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
        return new ThetaUnitActionStrategy();
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
        return new ThetaProductionChangeStrategy();
    }
}