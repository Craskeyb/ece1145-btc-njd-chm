package hotciv.standard;

import java.util.HashMap;

import hotciv.framework.*;

public class AlphaWinningStrategy implements WinningStrategy{
   @Override
    public Player getWinner(int year, HashMap<Position, City> cities, Game game){
        if (year == -3000) {
            return Player.RED;
          }
        return null;
    }
}
