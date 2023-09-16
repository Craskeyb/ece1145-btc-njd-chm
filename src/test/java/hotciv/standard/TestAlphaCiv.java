package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private Game game;

  private Unit unit;
  private City redCity;
  private City blueCity;

  private City city;


  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();

    unit = new UnitImpl(Player.RED,"ARCHER");
    redCity = new CityImpl(Player.RED);
    blueCity = new CityImpl(Player.BLUE);

    city = new CityImpl();

  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void populationIsOne(){
    assertThat(redCity, is(notNullValue()));
    assertThat(redCity.getSize(), is(1));
  }
  @Test
  public void redPositionCorrect(){
    assertThat(game, is(notNullValue()));
    assertThat(redCity, is(notNullValue()));
    assertThat(redCity.getOwner(), is(Player.RED));//Check if red
    Position testPos = redCity.getPosition();
    String pos = testPos.toString();
    assertThat(pos, is("[1,1]"));
  }
  @Test
  public void bluePositionCorrect(){
    assertThat(game, is(notNullValue()));
    assertThat(blueCity, is(notNullValue()));
    assertThat(blueCity.getOwner(), is(Player.BLUE));//Check if red
    Position testPos = blueCity.getPosition();
    String pos = testPos.toString();
    assertThat(pos, is("[4,1]"));
  }


  @Test public void checkTiles(){
    game = new GameImpl();
    Position oc = new Position(1,0);//Ocean
    Position hill = new Position(0,1);//Hill
    Position mountain = new Position(2,2);//Mountains
    Position plain = new Position(5,8);//Random Plain Position
    assertThat(game.getTileAt(oc).getTypeString(),is("ocean"));
    assertThat(game.getTileAt(hill).getTypeString(),is("hills"));
    assertThat(game.getTileAt(mountain).getTypeString(),is("mountain"));
    assertThat(game.getTileAt(plain).getTypeString(),is("plains"));

  @Test
  public void redCantMoveBlueUnits(){
    assertThat(game.getPlayerInTurn(),is(Player.RED));
    assertThat(game.moveUnit(new Position(3,2),new Position(5,5)), is(false));

  }


  /** REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has... */
  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }
}
