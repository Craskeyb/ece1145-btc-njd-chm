package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.AgingStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinningStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Develop the BetaCiv variant using TDD by refactoring the AlphaCiv production
 * code. Both variants must be maintained.
 * 1. Sketch a compositional design for the HotCiv system that supports the variants.
 * 2. Refactor the AlphaCiv production code to implement your design. Ensure your
 *    AlphaCiv passes all test cases before starting to implement BetaCiv. I advise to
 *    put common code into a package, like hotciv.common, and variant code in some
 *    other package, like hotciv.variants.
 * 3. Implement the BetaCiv variant using TDD.
 *
 * Exercise 36.5 (BetaCiv) - Christian
 * Identical to AlphaCiv with the following changes:
 * Winner is the first player who conquers all cities
 * World aging has a new algorithm to be followed (p. 463 in TB)
 * Between 4000BC and 100BC    100 years pass per round.
 * Around birth of Christ  the sequence is -100, -1, +1, +50.
 * Between 50AD and 1750   50 years pass per round.
 * Between 1750 and 1900   25 years pass per round.
 * Between 1900 and 1970   5 years per round.
 * After 1970  1 year per round.
 */
public class TestBetaCiv {
    private AgingStrategy agingStrategy;
    private WinningStrategy winningStrategy;
    private Game game;

    @Before
    public void setUp() {
        agingStrategy = new BetaAgingStrategy();
        winningStrategy = new BetaWinningStrategy();
        game = new GameImpl(new BetaCivFactory());
    }

    @Test
    public void shouldDecrease100YearsAtEndOfRound() {
        assertEquals(-3900, agingStrategy.ageWorld(-4000));
    }

    @Test
    public void shouldAgeAroundTheBirthOfChrist() {
        assertEquals(-100, agingStrategy.ageWorld(-200));
        assertEquals(-1, agingStrategy.ageWorld(-100));
        assertEquals(1, agingStrategy.ageWorld(-1));
        assertEquals(50, agingStrategy.ageWorld(1));
    }

    @Test
    public void shouldIncrease50YearsAtEndOfRound() {
        assertEquals(100, agingStrategy.ageWorld(50));
    }

    @Test
    public void shouldIncrease25YearsAtEndOfRound() {
        assertEquals(1775, agingStrategy.ageWorld(1750));
    }

    @Test
    public void shouldIncrease5YearsAtEndOfRound() {
        assertEquals(1905, agingStrategy.ageWorld(1900));
    }

    @Test
    public void shouldIncrease1YearAtEndOfRound() {
        assertEquals(2001, agingStrategy.ageWorld(2000));
    }

    @Test
    public void shouldReturnNullWhenCitiesHaveDifferentOwners() {
        HashMap<Position, City> cities = new HashMap<>();
        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        cities.put(new Position(1, 2), new CityImpl(Player.BLUE));

        assertNull(winningStrategy.getWinner(0, cities, game));
    }

    @Test
    public void shouldReturnRedAsWinnerWhenRedOwnsAllCities() {
        HashMap<Position, City> cities = new HashMap<>();
        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        cities.put(new Position(1, 2), new CityImpl(Player.RED));

        assertEquals(Player.RED, winningStrategy.getWinner(0, cities, game));
    }
}
