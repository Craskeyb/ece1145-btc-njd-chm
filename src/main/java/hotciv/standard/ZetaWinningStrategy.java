package hotciv.standard;

import hotciv.framework.*;
import java.util.HashMap;

public class ZetaWinningStrategy implements WinningStrategy {
    private final WinningStrategy BetaWinningStrategy;
    private final WinningStrategy EpsilonWinningStrategy;
    private WinningStrategy currentState;
    private boolean Reset;

    public ZetaWinningStrategy(WinningStrategy BetaWinningStrategy, WinningStrategy EpsilonWinningStrategy) {
        this.BetaWinningStrategy = BetaWinningStrategy;
        this.EpsilonWinningStrategy = EpsilonWinningStrategy;
        this.currentState = null;
        this.Reset = false;
    }

    @Override
    public Player getWinner(int year, HashMap<Position, City> cities, GameImpl game) {
        int round = game.getAge();

        if (round < 20) {
            currentState = BetaWinningStrategy;
        } else {
            game.endOfTurn();
            currentState = EpsilonWinningStrategy;
        }

        return currentState.getWinner(year, cities, game);
    }
}
