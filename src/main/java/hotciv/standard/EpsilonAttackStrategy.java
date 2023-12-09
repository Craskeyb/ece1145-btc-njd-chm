package hotciv.standard;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.GameSetupStrategy;
import hotciv.framework.Position;
import hotciv.framework.Player;
import java.util.Random;


public class EpsilonAttackStrategy implements AttackStrategy {
    public Boolean decideAttack(Game game, GameSetupStrategy mapStrategy, Position to, Position from){
        //First getting the combined attack power using helper function
        int combinedAttack = this.combinedStrength(game, from);

        int combinedDefense = this.combinedStrength(game, to);
        
        //Picking two random values for the outcome
        Random rand = new Random();
        //int attRand = rand.nextInt(1,6);
        //int defRand = rand.nextInt(1,6);
        int attRand = rand.nextInt(6);
        int defRand = rand.nextInt(6);

        //Performing outcome calculation wtih random values & combined strength values
        if(attRand*combinedAttack > defRand*combinedDefense){
            Player attacker = game.getUnitAt(to).getOwner();

            if(attacker == Player.RED){
                game.setAttacks(game.getRedAttacks()+1,game.getBlueAttacks());
            }
            else{
                game.setAttacks(game.getRedAttacks(),game.getBlueAttacks()+1);
            }
            mapStrategy.getUnitMap().put(to,game.getUnitAt(from));
            mapStrategy.getUnitMap().remove(from);
            return true;
        }
        else{
            mapStrategy.getUnitMap().remove(from);
            return false;
        }
    }

    //Method for getting combined attacking or defensive strength
    public int combinedStrength(Game game, Position p){
        Utility2 utility = new Utility2();
        int combinedStr;
        
        //Case for attacking player
        if(game.getPlayerInTurn() == game.getUnitAt(p).getOwner()){
            combinedStr = game.getUnitAt(p).getAttackingStrength();
            combinedStr += utility.getFriendlySupport(game, p, game.getUnitAt(p).getOwner());
            combinedStr *= utility.getTerrainFactor(game,p);
        }
        //Case for Defending Player
        else{
            combinedStr = game.getUnitAt(p).getDefensiveStrength();
            combinedStr += utility.getFriendlySupport(game, p, game.getUnitAt(p).getOwner());
            combinedStr *= utility.getTerrainFactor(game,p);
        }
        return combinedStr;
    }
}
