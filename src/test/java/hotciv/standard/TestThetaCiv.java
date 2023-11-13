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

    game.toggleTranscripts();
    assertThat(game.moveUnit(new Position(1,1), new Position(1,2)), is(true));
    TranscriptObserver transcript = (TranscriptObserver)game.getTranscript();
    String action = transcript.getTranscript().get(0);
    assertEquals(action, "Player RED moves ufo at (1,1) to (1,2).");

    assertThat(game.moveUnit(new Position(1,2), new Position(1,3)), is(true));
    String action2 = transcript.getTranscript().get(1);
    assertEquals(action2, "Player RED moves ufo at (1,2) to (1,3).");
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

  @Test
  public void ufoTurnsForestToPlains(){
    GameStubForUFOTesting game = new GameStubForUFOTesting();
    
    assertThat(game.getTileAt(new Position(1,1)).getTypeString(), is(GameConstants.FOREST));
    game.performUnitActionAt(new Position(1,1));
    assertThat(game.getTileAt(new Position(1,1)).getTypeString(), is(GameConstants.PLAINS));
  }
}

class GameStubForUFOTesting implements Game {
  private int redAttacks;
  private int blueAttacks;
  private Tile forestTest = new TileImpl(GameConstants.FOREST);
  private HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();
  private UnitActionStrategy thetaStrat = new ThetaUnitActionStrategy();
  
  public Tile getTileAt(Position p) {
    return forestTest;
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
        public int getTreasury() {return 0;}
        public String getProduction() {return null;}
        public String getWorkforceFocus() {return null;}
        public void decreaseTreasury(int amount) {}
        public void changeProduction(String prod) {}
        @Override
        public void setTreasury(int value) {}
        @Override
        public void setWorkforceFocus(String balance) {}
        public void setOwner(Player p) {}
        public void increaseTreasury() {}
      };}
      else if ( p.getRow() == 4 && p.getColumn() == 4) {}
      // return new City() {
      //   public Player getOwner() { return Player.BLUE; }
      //   public int getSize() { return 1; }
      //   public int getTreasury() {return 0;}
      //   public String getProduction() {return null;}
      //   public String getWorkforceFocus() {return null;}
      //   public void decreaseTreasury(int amount) {}
      //   public void changeProduction(String prod) {}
      //   public void setTreasury(int value) {}
      //   public void setWorkforceFocus(String balance) {}

      //   public void setOwner(Player p) {}
      //   public void increaseTreasury() {}
      // };}
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

  public void performUnitActionAt( Position p ) {
    unitMap.put(new Position(1,1), new UnitImpl(Player.RED, "ufo"));
    thetaStrat.performUnitActionAt(p, unitMap, this);
  }

  public int getRedAttacks() {return redAttacks;}
  public int getBlueAttacks() {return blueAttacks;}
  public void removeUnit(Position p) {}
  public void createCity(Position p) {}
  public Position getOpenPosition(Position p) {return new Position(0,0);} 
  public void setAttacks(int red, int blue) {redAttacks = red; blueAttacks = blue;}
  public void removeCity(Position p) {}
  public void toggleTranscripts() {}
  public TranscriptObserver getTranscript() {return new TranscriptObserver();}
}
