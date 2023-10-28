package hotciv.standard;

import hotciv.framework.*;

public class ZetaCivFactory implements AbstractFactory {

    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new ZetaWinningStrategy(new BetaWinningStrategy(), new EpsilonWinningStrategy());
    }

    @Override
    public GameSetupStrategy createGameSetup(){
        return new MapImpl();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        // Uncomment the next line if you have the `AlphaCivActionStrategy` defined and available
        // return new AlphaCivActionStrategy();
        return null;
    }

    @Override
    public AttackStrategy createAttackStrategy() {
        return new AlphaAttackStrategy();

    }
}
