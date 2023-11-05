package hotciv.standard;

import hotciv.framework.*;

public class SemiCivFactory implements AbstractFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public GameSetupStrategy createGameSetup() {return new DeltaMapImpl();}

    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonWinningStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    @Override
    public AttackStrategy createAttackStrategy() {return new EpsilonAttackStrategy();}

    @Override
    public WorkforceStrategy createWorkforceStrategy(){return new AlphaWorkforceStrategy();}

}
