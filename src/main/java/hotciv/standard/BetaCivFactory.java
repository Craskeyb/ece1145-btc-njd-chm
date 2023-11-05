package hotciv.standard;

import hotciv.framework.*;

public class BetaCivFactory implements AbstractFactory {

    @Override
    public AgingStrategy createAgingStrategy() {return new BetaAgingStrategy();}

    @Override
    public GameSetupStrategy createGameSetup() {
        return new MapImpl();
    }

    @Override
    public WinningStrategy createWinningStrategy() {return new BetaWinningStrategy();}
     
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
