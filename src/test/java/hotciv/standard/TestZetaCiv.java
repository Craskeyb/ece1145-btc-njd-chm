package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestZetaCiv {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new ZetaCivFactory());
    }

    @Test
    public void shouldHaveNoWinnerWhenNotAllCitiesAreConquered() {
        assertNull(game.getWinner());
    }

    @Test
    public void shouldDeclareRedAsWinnerWhenRedOwnsAllCities() {
        game.getCityAt(new Position(4,1)).setOwner(Player.RED);
        //CityImpl city = (CityImpl) game.getCityAt(new Position(4,1));
        //if (city != null) {
        //    city.setOwner(Player.RED);
        //}
        assertThat(game.getAge(), is(-4000));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldHaveNoWinnerAfter20RoundsWithoutThreeAttacks() {
        progressRounds(20);
        assertNull(game.getWinner());
    }

    @Test
    public void shouldDeclareWinnerAfter20RoundsBasedOnFirstThreeAttacks() {
        progressRounds(21);
        game.setAttacks(3, 0);
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void attackCountShouldBeConsideredOnlyAfter20Rounds() {
        game.setAttacks(3, 0);
        progressRounds(20);
        assertNull(game.getWinner());

        game.setAttacks(0, 3);
        endOfRound();
        assertThat(game.getWinner(), is(Player.BLUE));
    }

    private void progressRounds(int rounds) {
        for(int i=0; i<rounds; i++) {
            endOfRound();
        }
    }

    private void endOfRound() {
        game.endOfTurn();
        game.endOfTurn();
    }
}
