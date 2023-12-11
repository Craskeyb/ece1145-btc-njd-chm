package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestSemiCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory());
    }

    //Ages like Beta Civ
    @Test
    public void gameAgesCorrectly(){
        int age = game.getAge();
        assertThat(age,is(-4000));
        int newAge;
        for(int i=0; i<39;i++){
            age = game.getAge();
            game.endOfTurn();
            newAge = game.getAge();
            assertThat(age - newAge,is(-100));
        }
        //Sequence around 0
        assertThat(game.getAge(),is(-100));
        game.endOfTurn();
        assertThat(game.getAge(),is(-1));
        game.endOfTurn();
        assertThat(game.getAge(),is(1));
        game.endOfTurn();
        assertThat(game.getAge(),is(50));

        //50 years per round
        while (game.getAge() < 1750){
            age = game.getAge();
            game.endOfTurn();
            newAge = game.getAge();
            assertThat(newAge - age,is(50));
        }
        assertThat(game.getAge(),is(1750));

        //25 years per round
        while (game.getAge() < 1900){
            age = game.getAge();
            game.endOfTurn();
            newAge = game.getAge();
            assertThat(newAge - age,is(25));
        }
        assertThat(game.getAge(),is(1900));

        //5 years oer round
        while (game.getAge() < 1970){
            age = game.getAge();
            game.endOfTurn();
            newAge = game.getAge();
            assertThat(newAge - age,is(5));
        }
        assertThat(game.getAge(),is(1970));

        //1 year per round
        while (game.getAge() < 1980){
            age = game.getAge();
            game.endOfTurn();
            newAge = game.getAge();
            assertThat(newAge - age,is(1));
        }
    }

    //Map like delta civ
    @Test
    public void checkCities() {
        Position testRed = new Position(8,12);
        Position testBlue = new Position(4, 5);
        assertThat(game.getCityAt(testRed).getOwner(),is(Player.RED));
        assertThat(game.getCityAt(testBlue).getOwner(),is(Player.BLUE));
    }

    @Test
    public void spotTest(){
        //Test 5 random positions
        Position pos1 = new Position(1,1); //Ocean
        Position pos2 = new Position(0,5); //Mountain
        Position pos3 = new Position(7,1); //Plain
        Position pos4 = new Position(1,9); //Forrest
        Position pos5 = new Position(14,5); //Hills
        assertThat(game.getTileAt(pos1).getTypeString(), is("ocean"));
        assertThat(game.getTileAt(pos2).getTypeString(), is("mountain"));
        assertThat(game.getTileAt(pos3).getTypeString(), is("plains"));
        assertThat(game.getTileAt(pos4).getTypeString(), is("forest"));
        assertThat(game.getTileAt(pos5).getTypeString(), is("hills"));
    }

    //Epsilon Winning Strategy
    @Test public void noWinnerWithout3SuccessfulAttacks() {
        assertThat(game.getWinner(), is(nullValue()));
    }
    @Test public void winnerAt3Attacks() {
        game.setAttacks(3,0);
        assertThat(game.getWinner(), is(Player.RED));
    }

    //Gamma Unit Action Strategy
    @Test
    public void settlerAction() {
        //Red settler is at 4,3
        game.performUnitActionAt(new Position(4,3));
        assertThat(game.getUnitAt(new Position(4,3)), is(nullValue(null)));
        assertThat(game.getCityAt(new Position(4,3)).getOwner(), is(game.getPlayerInTurn()));
    }
    @Test
    public void archerAction() {
        Unit archer = game.getUnitAt(new Position(2,1));
        assertThat(archer.getDefensiveStrength(), is(3));
        game.performUnitActionAt(new Position(2,1));
        assertThat(archer.getDefensiveStrength(),is(6));
        game.performUnitActionAt(new Position(2,1));
        assertThat(archer.getDefensiveStrength(), is(3));
    }

    //Treasury Updates like Alpha
    @Test
    public void produceSixProduction() {
        Position redCity = new Position(8,12);
        Position blueCity = new Position(4,5);
        assertThat(game.getCityAt(redCity).getTreasury(),is(0));
        assertThat(game.getCityAt(blueCity).getTreasury(),is(0));
        game.endOfTurn();
        assertThat(game.getCityAt(redCity).getTreasury(),is(6));
        assertThat(game.getCityAt(blueCity).getTreasury(),is(0));
        game.endOfTurn();
        assertThat(game.getCityAt(redCity).getTreasury(),is(6));
        assertThat(game.getCityAt(blueCity).getTreasury(),is(6));
    }

}
