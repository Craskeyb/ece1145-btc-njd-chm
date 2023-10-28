package hotciv.framework;
import hotciv.standard.GameImpl;
import java.util.HashMap;

public interface WinningStrategy {
    /**
     * Interface in framework for betaciv, implemented by BetaWinningStrategy in standard filepath
     * Determines the winner of the game based on the given year and cities on the map.
        year :  the current year in the game.
     * cities: a HashMap containing the positions of cities on the map.
     * @return the Player object representing the winner, or null if there is no winner yet.
     */
    public Player getWinner(int year, HashMap<Position, City> cities, GameImpl game);



}