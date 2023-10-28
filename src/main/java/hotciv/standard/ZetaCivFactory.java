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
    public GameSetupStrategy createGameSetup() {
        // Uncomment the next line if you have the `AlphaCivGameSetupStrategy` defined and available
        // return new AlphaCivGameSetupStrategy();
        return null;
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        // Uncomment the next line if you have the `AlphaCivActionStrategy` defined and available
        // return new AlphaCivActionStrategy();
        return null;
    }

    @Override
    public AttackStrategy createAttackStrategy() {
        // Uncomment the next line if you have the `AlphaWorldAttackStrategy` defined and available
        // return new AlphaWorldAttackStrategy();
        return null;
    }
}
