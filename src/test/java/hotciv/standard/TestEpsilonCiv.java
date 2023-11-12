package hotciv.standard;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author Henrik BÃ¦rbak Christensen, Aarhus University
 */
public class TestEpsilonCiv {

  Game game;
  Utility2 Utility2;
  EpsilonAttackStrategy epsilonAttack;
  WinningStrategy epsilonWinner;
  @Before
  public void setUp() {
    game = new GameStubForBattleTesting();
    Utility2 = new Utility2();
    epsilonAttack =  new EpsilonAttackStrategy();
    epsilonWinner = new EpsilonWinningStrategy();
  }

  @Test public void shouldGiveCorrectTerrainFactors() {
    
    // plains have multiplier 1
    assertThat(Utility2.getTerrainFactor(game, new Position(0,1)), is(1));
    // hills have multiplier 2
    assertThat(Utility2.getTerrainFactor(game, new Position(1,0)), is(2));
    // forest have multiplier 2
    assertThat(Utility2.getTerrainFactor(game, new Position(0,0)), is(2));
    // cities have multiplier 3
    assertThat(Utility2.getTerrainFactor(game, new Position(1,1)), is(3));
  }

  @Test public void shouldGiveSum1ForBlueAtP5_5() {
    assertThat("Blue unit at (5,5) should get +1 support",
            Utility2.getFriendlySupport( game, new Position(5,5), Player.BLUE), is(+1));
  }

  @Test public void shouldGiveSum0ForBlueAtP2_4() {
    assertThat("Blue unit at (2,4) should get +0 support",
            Utility2.getFriendlySupport( game, new Position(2,4), Player.BLUE), is(+0));
  }
  @Test public void shouldGiveSum2ForRedAtP2_4() {
    assertThat("Red unit at (2,4) should get +2 support",
            Utility2.getFriendlySupport( game, new Position(2,4), Player.RED), is(+2));
  }
  @Test public void shouldGiveSum3ForRedAtP2_2() {
    assertThat("Red unit at (2,2) should get +3 support",
            Utility2.getFriendlySupport( game, new Position(2,2), Player.RED), is(+3));
  }


  @Test public void shouldGiveStrength9forBlueAtP4_4() {
    assertThat("Blue unit at (4,4) should have a total defensive strength of 9", epsilonAttack.combinedStrength(game, new Position(4,4)), is(9));
  }

  @Test public void shouldGiveStrength4forRedAtP3_2() {
    assertThat("Red unit at (3,2) should have a total attack strength of 4", epsilonAttack.combinedStrength(game, new Position(3,2)), is(4));
  }

  @Test public void shouldGiveStrength3forRedAtP3_3() {
    assertThat("Red unit at (3,3) should have a total attack strength of 4", epsilonAttack.combinedStrength(game, new Position(3,3)), is(4));
  }

  @Test public void shouldGiveStrength4forRedAtP2_3() {
    assertThat("Red unit at (2,3) should have a total attack strength of 4", epsilonAttack.combinedStrength(game, new Position(2,3)), is(4));
  }
  
  @Test public void noWinnerWithout3SuccessfulAttacks() {
    assertThat("No player should be declared winner if neither has had 3 successful attacks", epsilonWinner.getWinner(game.getAge(), new HashMap<Position,City>(), game), is(nullValue()));
  }

  @Test public void winnerAt3Attacks() {
    game.setAttacks(3,0);
    assertThat("Red should win if they've had 3 successful attacks", epsilonWinner.getWinner(game.getAge(), new HashMap<Position,City>(), game), is(Player.RED));

  }
}

// ================================== TEST STUBS ===
class StubTile implements Tile {
  private String type;
  public StubTile(String type, int r, int c) { this.type = type; }
  public String getTypeString() { return type; }
  public void setTypeString(String type) { this.type = type; }
}

class StubUnit implements Unit {
  private String type; 
  private Player owner;
  private int defStr;
  private int attStr;
  private int moveCt;
  public StubUnit(String type, Player owner) {
    this.type = type; 
    this.owner = owner;
    if(type == GameConstants.ARCHER){
            defStr = 3;
            attStr = 2;
        }
        else if(type == GameConstants.SETTLER){
            defStr = 3;
            attStr = 0;
        }
        else{
            defStr = 2;
            attStr = 4;
        }
        moveCt = 1;
  }
  public String getTypeString() { return type; }
  public Player getOwner() { return owner; }
  public int getMoveCount() { return moveCt; }
  public void resetMoveCount() { this.moveCt = 1; }
  public int getDefensiveStrength() { return defStr; }
  public int getAttackingStrength() { return attStr; }
  public void setAttackingStrength(int strength) {}
  public void setDefensiveStrength(int strength) {}
  public void decreaseMoveCount() {}
}


/** A test stub for testing the battle calculation methods in
 * Utility. The terrains are
 * 0,0 - forest;
 * 1,0 - hill;
 * 0,1 - plain;
 * 1,1 - city.
 *
 * Red has units on 2,3; 3,2; 3,3; blue one on 4,4
 */
class GameStubForBattleTesting implements Game {
  private int redAttacks;
  private int blueAttacks;
  
  public Tile getTileAt(Position p) {
    if ( p.getRow() == 0 && p.getColumn() == 0 ) {
      return new StubTile(GameConstants.FOREST, 0, 0);
    }
    if ( p.getRow() == 1 && p.getColumn() == 0 ) {
      return new StubTile(GameConstants.HILLS, 1, 0);
    }
    return new StubTile(GameConstants.PLAINS, 0, 1);
  }
  public Unit getUnitAt(Position p) {
    if ( p.getRow() == 2 && p.getColumn() == 3 ||
            p.getRow() == 3 && p.getColumn() == 2 ||
            p.getRow() == 3 && p.getColumn() == 3 ) {
      return new StubUnit(GameConstants.ARCHER, Player.RED);
    }
    if ( p.getRow() == 4 && p.getColumn() == 4 ) {
      return new StubUnit(GameConstants.ARCHER, Player.BLUE);
    }
    return null;
  }
  public City getCityAt(Position p) {
    if ( p.getRow() == 1 && p.getColumn() == 1 ) {
      return new City() {
        public Player getOwner() { return Player.RED; }
        public int getSize() { return 1; }
        public int getTreasury() {
          return 0;
        }
        public String getProduction() {
          return null;
        }
        public String getWorkforceFocus() {
          return null;
        }
        public void decreaseTreasury(int amount) {}
        public void changeProduction(String prod) {}

        @Override
        public void setTreasury(int value) {

        }

        @Override
        public void setWorkforceFocus(String balance) {

        }

        public void setOwner(Player p) {}
        public void increaseTreasury() {}
      };}
      else if ( p.getRow() == 4 && p.getColumn() == 4) {
      return new City() {
        public Player getOwner() { return Player.BLUE; }
        public int getSize() { return 1; }
        public int getTreasury() {
          return 0;
        }
        public String getProduction() {
          return null;
        }
        public String getWorkforceFocus() {
          return null;
        }
        public void decreaseTreasury(int amount) {}
        public void changeProduction(String prod) {}

        @Override
        public void setTreasury(int value) {

        }

        @Override
        public void setWorkforceFocus(String balance) {

        }

        public void setOwner(Player p) {}
        public void increaseTreasury() {}
      };
      }
    return null;
  }

  // the rest is unused test stub methods...
  public void changeProductionInCityAt(Position p, String unitType) {}
  public void changeWorkForceFocusInCityAt(Position p, String balance) {}
  public void endOfTurn() {}
  public Player getPlayerInTurn() {return Player.RED;}
  public Player getWinner() {return null;}
  public int getAge() { return 0; }
  public boolean moveUnit(Position from, Position to) {return false;}
  public void performUnitActionAt( Position p ) {}
  public int getRedAttacks() {return redAttacks;}
  public int getBlueAttacks() {return blueAttacks;}
  public void removeUnit(Position p) {}
  public void createCity(Position p) {}
  public Position getOpenPosition(Position p) {return new Position(0,0);} 
  public void setAttacks(int red, int blue) {redAttacks = red; blueAttacks = blue;}
  public void removeCity(Position p) {}
}