package hotciv.standard;

import hotciv.framework.AbstractFactory;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.GameSetupStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.framework.UnitActionStrategy;

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
}
