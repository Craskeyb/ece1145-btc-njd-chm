package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinningStrategy;

import java.util.HashMap;

public class EpsilonWinningStrategy implements WinningStrategy {
    public int redAttacks = 0;
    public int blueAttacks = 0;
    
    @Override
    public Player getWinner(int year, HashMap<Position, City> cities) {
        if(redAttacks < 3 && blueAttacks < 3){
            return null;
        }
        else{
            if(redAttacks == 3){
                return Player.RED;
            }
            else{
                return Player.BLUE;
            }
        }
    }

    public void incrementSuccessfulAttacks(Player attacker){
        if(attacker == Player.RED){
            redAttacks++;
        }
        else{
            blueAttacks++;
        }
    }
}
