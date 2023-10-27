package hotciv.standard;
import hotciv.framework.*;

public class AlphaAttackStrategy implements AttackStrategy{
    public Boolean decideAttack(Game game, GameSetupStrategy mapStrategy, Position to, Position from){
        mapStrategy.getUnitMap().put(to,game.getUnitAt(from));
        mapStrategy.getUnitMap().remove(from);
        return true;
    }
}
