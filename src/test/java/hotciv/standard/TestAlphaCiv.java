package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


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


  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();

  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void populationIsOne(){

    assertThat(game.getCityAt(new Position(1,1)), is(notNullValue()));
    assertThat(game.getCityAt(new Position(1,1)).getSize(), is(1));
  }
  @Test
  public void redPositionCorrect(){
    assertThat(game, is(notNullValue()));
    assertThat(game.getCityAt(new Position(1,1)), is(notNullValue()));
    assertThat(game.getCityAt(new Position(1,1)).getOwner(), is(Player.RED));//Check if red
  }

  @Test
  public void bluePositionCorrect(){
    assertThat(game, is(notNullValue()));
    assertThat(game.getCityAt(new Position(4, 1)), is(notNullValue()));
    assertThat(game.getCityAt(new Position(4,1)).getOwner(), is(Player.BLUE));//Check if red
  }




  @Test public void checkTiles() {
    game = new GameImpl();
    Position oc = new Position(1, 0);//Ocean
    Position hill = new Position(0, 1);//Hill
    Position mountain = new Position(2, 2);//Mountains
    Position plain = new Position(5, 8);//Random Plain Position
    assertThat(game.getTileAt(oc).getTypeString(), is("ocean"));
    assertThat(game.getTileAt(hill).getTypeString(), is("hills"));
    assertThat(game.getTileAt(mountain).getTypeString(), is("mountain"));
    assertThat(game.getTileAt(plain).getTypeString(), is("plains"));
  }

  @Test
  public void cantMoveOverMountain() {
  //todo

  }

  @Test
  public void checkMountain(){
    Position mountain = new Position(2,2);//Mountains
    assertThat(game.getTileAt(mountain).getTypeString(),is("mountain"));
  }
  
  @Test
  public void checkHill(){
    Position hill = new Position(0,1);//Hill
    assertThat(game.getTileAt(hill).getTypeString(),is("hills"));
  }

  @Test
  public void checkOcean(){
    Position oc = new Position(1,0);//Ocean
    assertThat(game.getTileAt(oc).getTypeString(),is("ocean"));
  }
  


  @Test
  public void redCantMoveBlueUnits(){
    assertThat(game.getPlayerInTurn(),is(Player.RED));
    assertThat(game.moveUnit(new Position(3,2),new Position(5,5)), is(false));

  }

  @Test


  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));
  public void cantMoveOnMountain(){
    Game newGame = new GameImpl();
    assertThat(newGame.moveUnit(new Position(2, 0), new Position(2,2)), is(false));
  }

  @Test
  public void gameStartsAt4000(){
    assertThat(game.getAge(), is(4000));
  }

  @Test
  public void blueIsAfterRedInTurn(){
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(),is(Player.BLUE));
  }

  @Test
  public void gameAges100(){
    Game newGame = new GameImpl();
    assertThat(newGame.getAge(), is(4000));
    newGame.endOfTurn();
    assertThat(newGame.getAge(),is(3900));
  }

  @Test
  public void redWinsAt3000(){
    Game newGame = new GameImpl();
    for(int i = 0; i<10;i++){
      assertThat(newGame.getWinner(),is(nullValue()));
      newGame.endOfTurn();
    }
    assertThat(newGame.getAge(),is(3000));
    assertThat(newGame.getWinner(), is(Player.RED));
  }
@Test
  public void produceSixProduction() {
  Game newGame = new GameImpl();
  Position redCity = new Position(1,1);
  Position blueCity = new Position(4,1);
  assertThat(newGame.getCityAt(redCity).getTreasury(),is(0));
  assertThat(newGame.getCityAt(blueCity).getTreasury(),is(0));
  newGame.endOfTurn();
  assertThat(newGame.getCityAt(redCity).getTreasury(),is(6));
  assertThat(newGame.getCityAt(blueCity).getTreasury(),is(0));
  newGame.endOfTurn();
  assertThat(newGame.getCityAt(redCity).getTreasury(),is(6));
  assertThat(newGame.getCityAt(blueCity).getTreasury(),is(6));
}



  @Test
  public void redHasArcherAt2_0(){
    Game newGame = new GameImpl();
    assertThat(newGame.getUnitAt(new Position(2,0)).getOwner(), is(Player.RED));
    assertThat(newGame.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void blueHasLegionAt3_2(){
    Game newGame = new GameImpl();
    assertThat(newGame.getUnitAt(new Position(3,2)).getTypeString(), is(GameConstants.LEGION));
    assertThat(newGame.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void redHasSettlerAt4_3(){
    Game newGame = new GameImpl();
    assertThat(newGame.getUnitAt(new Position(4,3)).getOwner(), is(Player.RED));
    assertThat(newGame.getUnitAt(new Position(4,3)).getTypeString(), is(GameConstants.SETTLER));
  }

  @Test
  public void attackerAlwaysWins(){
    //Testing that red unit wins
    Game newGame = new GameImpl();
    assertThat(newGame.moveUnit(new Position(2,0),new Position(3,2)),is(true));
    assertThat(newGame.getUnitAt(new Position(3,2)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(newGame.getUnitAt(new Position(3,2)).getOwner(), is(Player.RED));


    //Testing that blue unit wins
    Game newGame2 = new GameImpl();
    newGame2.endOfTurn();
    assertThat(newGame2.moveUnit(new Position(3,2),new Position(2,0)),is(true));
    assertThat(newGame2.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.LEGION));
    assertThat(newGame2.getUnitAt(new Position(2,0)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void newUnitSpawns(){
    Game newGame = new GameImpl();
    assertThat(newGame.getUnitAt(new Position(1,1)), is(nullValue()));
    assertThat(newGame.getCityAt(new Position(1,1)).getTreasury(), is(0));
    
    //Need to end turn twice to signal a new round starting
    newGame.endOfTurn();
    newGame.endOfTurn();

    assertThat(newGame.getUnitAt(new Position(1,1)), is(nullValue()));
    assertThat(newGame.getCityAt(new Position(1,1)).getTreasury(), is(6));
    
    newGame.endOfTurn();
    newGame.endOfTurn();

    assertThat(newGame.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(newGame.getCityAt(new Position(1,1)).getTreasury(), is(2));

    newGame.endOfTurn();
    newGame.endOfTurn();

    assertThat(newGame.getUnitAt(new Position(0,1)), is(nullValue()));
    assertThat(newGame.getCityAt(new Position(1,1)).getTreasury(), is(8));
    
    newGame.endOfTurn();
    newGame.endOfTurn();

    assertThat(newGame.getUnitAt(new Position(0,1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(newGame.getCityAt(new Position(1,1)).getTreasury(), is(4));
  }

  @Test
  public void cityProductionChange(){
    assertThat(game.getCityAt(new Position(1,1)).getProduction(),is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
    assertThat(game.getCityAt(new Position(1,1)).getProduction(),is(GameConstants.LEGION));
  }
}
  

  /** REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has... */
//   @Test
//   public void shouldDefinetelyBeRemoved() {
//     // Matching null and not null values
//     // 'is' require an exact match
//     String s = null;
//     assertThat(s, is(nullValue()));
//     s = "Ok";
//     assertThat(s, is(notNullValue()));
//     assertThat(s, is("Ok"));

//     // If you only validate substrings, use containsString
//     assertThat("This is a dummy test", containsString("dummy"));

//     // Match contents of Lists
//     List<String> l = new ArrayList<String>();
//     l.add("Bimse");
//     l.add("Bumse");
//     // Note - ordering is ignored when matching using hasItems
//     assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));


