package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestDeltaCiv {
    private GameSetupStrategy delta;
    @Before
    public void setUp() {
        delta = new MapImpl();
    }

    @Test
    public void checkCities() {
        delta.setUpBoard();
        HashMap<Position,City> cityMapTest = delta.getCityMap();
        Position testRed = new Position(8,12);
        Position testBlue = new Position(4, 5);
        assertThat(cityMapTest.get(testRed).getOwner(),is(Player.RED));
        assertThat(cityMapTest.get(testBlue).getOwner(),is(Player.BLUE));
    }

    @Test
    public void spotTest(){
        delta.setUpBoard();
        HashMap<Position,Tile> tileTest = delta.getTileMap();
        //Test 5 random positions
        Position pos1 = new Position(1,1); //Ocean
        Position pos2 = new Position(0,5); //Mountain
        Position pos3 = new Position(7,1); //Plain
        Position pos4 = new Position(1,9); //Forrest
        Position pos5 = new Position(14,5); //Hills
        assertThat(tileTest.get(pos1).getTypeString(), is("ocean"));
        assertThat(tileTest.get(pos2).getTypeString(), is("mountain"));
        assertThat(tileTest.get(pos3).getTypeString(), is("plains"));
        assertThat(tileTest.get(pos4).getTypeString(), is("forest"));
        assertThat(tileTest.get(pos5).getTypeString(), is("hills"));
    }

}
