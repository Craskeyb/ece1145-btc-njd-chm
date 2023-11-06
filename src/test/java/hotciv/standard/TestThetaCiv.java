package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestThetaCiv {
  
  private Game game;


  /** Fixture for gammaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new ThetaCivFactory());
  }

  @Test
  public void ufoCanBeCreated(){
    //Production successfully changed to ufo
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), "ufo");
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is("ufo"));
    while(game.getCityAt(new Position(1,1)).getTreasury() < 54){
        game.endOfTurn();
    }
    game.endOfTurn();
    game.endOfTurn();
    //UFO is created once enough production has been accrued in the treasury
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("ufo"));
    assertThat(game.getUnitAt(new Position(1,1)).getAttackingStrength(), is(1));
    assertThat(game.getUnitAt(new Position(1,1)).getDefensiveStrength(), is(8));
  }

  @Test
  public void ufoCanMoveTwice(){
    //Production successfully changed to ufo
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), "ufo");
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is("ufo"));
    while(game.getCityAt(new Position(1,1)).getTreasury() < 54){
        game.endOfTurn();
    }
    game.endOfTurn();
    game.endOfTurn();
    while(game.getPlayerInTurn() != Player.RED){
        game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("ufo"));
    assertThat(game.moveUnit(new Position(1,1), new Position(1,2)), is(true));
    assertThat(game.moveUnit(new Position(1,2), new Position(1,3)), is(true));
  }

  @Test
  public void ufoCanMoveOnMountain(){
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), "ufo");
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is("ufo"));
    while(game.getCityAt(new Position(1,1)).getTreasury() < 54){
        game.endOfTurn();
    }
    game.endOfTurn();
    game.endOfTurn();
    while(game.getPlayerInTurn() != Player.RED){
        game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("ufo"));
    assertThat(game.moveUnit(new Position(1,1), new Position(2,2)), is(true));
  }

  @Test
  public void ufoCanMoveOnOcean(){
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), "ufo");
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is("ufo"));
    while(game.getCityAt(new Position(1,1)).getTreasury() < 54){
        game.endOfTurn();
    }
    game.endOfTurn();
    game.endOfTurn();
    while(game.getPlayerInTurn() != Player.RED){
        game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("ufo"));
    assertThat(game.moveUnit(new Position(1,1), new Position(0,1)), is(true));
  }

  @Test
  public void ufoCanMoveOnOpposingCity(){
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), "ufo");
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is("ufo"));
    while(game.getCityAt(new Position(1,1)).getTreasury() < 54){
        game.endOfTurn();
    }
    game.endOfTurn();
    game.endOfTurn();
    while(game.getPlayerInTurn() != Player.RED){
        game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("ufo"));
    assertThat(game.moveUnit(new Position(1,1), new Position(2,1)), is(true));
    assertThat(game.moveUnit(new Position(2,1), new Position(3,1)), is(true));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.moveUnit(new Position(3,1), new Position(4,1)), is(true));
  }

  @Test
  public void ufoCanReduceCityPopulation(){
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), "ufo");
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is("ufo"));
    while(game.getCityAt(new Position(1,1)).getTreasury() < 54){
        game.endOfTurn();
    }
    game.endOfTurn();
    game.endOfTurn();
    while(game.getPlayerInTurn() != Player.RED){
        game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("ufo"));
    assertThat(game.moveUnit(new Position(1,1), new Position(2,1)), is(true));
    assertThat(game.moveUnit(new Position(2,1), new Position(3,1)), is(true));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.moveUnit(new Position(3,1), new Position(4,1)), is(true));
    
    //After arriving at city tile, if the unit action is performed it should remove the city from the game, since population in alphaciv is always 1
    game.performUnitActionAt(new Position(4,1));
    assertThat(game.getCityAt(new Position(4,1)), is(nullValue()));
  }

  @Test
  public void ufoCanBattleNormally(){
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), "ufo");
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is("ufo"));
    while(game.getCityAt(new Position(1,1)).getTreasury() < 54){
        game.endOfTurn();
    }
    game.endOfTurn();
    game.endOfTurn();
    while(game.getPlayerInTurn() != Player.RED){
        game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("ufo"));
    game.moveUnit(new Position(1,1), new Position(2,2));
    assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));
    assertThat(game.moveUnit(new Position(2,2), new Position(3,2)), is(true));
    assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.RED));
    assertThat(game.getUnitAt(new Position(3,2)).getTypeString(), is("ufo"));
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

//     // Matchers may be combined, like is-not
//     assertThat(l.get(0), is(not("Bumse")));
//   }
// }


