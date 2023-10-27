package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinningStrategy;

import java.util.HashMap;

public class EpsilonWinningStrategy implements WinningStrategy {
    
    @Override
    public Player getWinner(int year, HashMap<Position, City> cities, Game game) {
        
        if(game.getRedAttacks() < 3 && game.getBlueAttacks() < 3){
            return null;
        }
        else{
            if(game.getRedAttacks() == 3){
                return Player.RED;
            }
            else{
                return Player.BLUE;
            }
        }
    }
}
