package hotciv.standard;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.GameSetupStrategy;
import hotciv.framework.Position;
import java.util.Random;

public class EpsilonAttackStrategy implements AttackStrategy {
    public Boolean decideAttack(Game game, GameSetupStrategy mapStrategy, Position to, Position from){
        Utility2 utility = new Utility2();

        //First getting the combined attack power
        int combinedAttack = game.getUnitAt(from).getAttackingStrength();
        combinedAttack += utility.getFriendlySupport(game, from, game.getUnitAt(from).getOwner());
        combinedAttack *= utility.getTerrainFactor(game,from);

        int combinedDefense = game.getUnitAt(to).getDefensiveStrength();
        combinedDefense += utility.getFriendlySupport(game, to, game.getUnitAt(to).getOwner());
        combinedDefense *= utility.getTerrainFactor(game, to);
        
        //Picking two random values for the outcome
        Random rand = new Random();
        int attRand = rand.nextInt(1,6);
        int defRand = rand.nextInt(1,6);

        if(attRand*combinedAttack > defRand*combinedDefense){
            return true;
        }
        else{
            return false;
        }
    }
}
