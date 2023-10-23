package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinningStrategy;

import java.util.HashMap;

public class BetaWinningStrategy implements WinningStrategy {

    @Override
    public Player getWinner(int year, HashMap<Position, City> cities) {
        Player lastOwner = null;

        // Iterate through all the cities on the map
        for (City city : cities.values()) {
            if (lastOwner == null) {
                // If it's the first city, set lastOwner to the city's owner
                lastOwner = city.getOwner();
            } else {
                // If the city's owner is different from the last city's owner, than no winner yet
                if (city.getOwner() != lastOwner) {
                    return null;
                }
            }
        }

        // Return the last owner (i.e. winner) if all cities have the same owner
        return lastOwner;
    }
}
