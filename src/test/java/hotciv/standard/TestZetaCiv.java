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

    private void progressRounds(int rounds) {
        for (int i = 0; i < rounds; i++) {
            endOfRound();
        }
    }

    private void endOfRound() {
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void shouldHaveNoWinnerAtGameStart() {
        assertNull(game.getWinner());
    }

    @Test
    public void shouldDeclareRedAsWinnerWhenRedOwnsAllCities() {
        game.getCityAt(new Position(4, 1)).setOwner(Player.RED);
        assertThat(game.getAge(), is(-4000));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldDeclareBlueAsWinnerWhenBlueOwnsAllCities() {
        game.getCityAt(new Position(1, 1)).setOwner(Player.BLUE);
        assertThat(game.getAge(), is(-4000));
        assertThat(game.getWinner(), is(Player.BLUE));
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

    @Test
    public void shouldDeclareWinnerAfter20RoundsBasedOnBlueFirstThreeAttacks() {
        progressRounds(21);
        game.setAttacks(0, 3);
        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void blueShouldBeWinnerIfTheyHaveMoreAttacksAfter20Rounds() {
        progressRounds(21);
        game.setAttacks(2, 3);
        assertThat(game.getWinner(), is(Player.BLUE));
    }


    @Test
    public void tiesInAttackCountAfter20RoundsShouldNotDeclareAWinner() {
        progressRounds(21);
        game.setAttacks(2, 2);
        assertNull(game.getWinner());
    }


}
