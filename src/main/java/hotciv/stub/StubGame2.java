package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.*;

import java.util.*;

/** Test stub for game for visual testing of
 * minidraw based graphics.
 *
 * SWEA support code.
 *
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

public class StubGame2 implements Game {

  // === Unit handling ===
  private Position pos_archer_red;
  private Position pos_legion_blue;
  private Position pos_settler_red;
  private Position pos_ufo_red;

  private Position city_pos;
  private Position blue_unit_pos;

  private Unit red_archer;

  private TranscriptObserver transcriptObserver = new TranscriptObserver();

  public Unit getUnitAt(Position p) {
    if ( p.equals(pos_archer_red) ) {
      return red_archer;
    }
    if ( p.equals(pos_settler_red) ) {
      return new StubUnit( GameConstants.SETTLER, Player.RED );
    }
    if ( p.equals(pos_legion_blue) ) {
      return new StubUnit( GameConstants.LEGION, Player.BLUE );
    }
    if ( p.equals(pos_ufo_red) ) {
      return new StubUnit( ThetaConstants.UFO, Player.RED );
    }
    if ( p. equals(blue_unit_pos)) {
      return blueUnit;
    }
    return null;
  }

  // Stub only allows moving red archer
  public boolean moveUnit( Position from, Position to ) { 
    System.out.println( "-- StubGame2 / moveUnit called: "+from+"->"+to );
    if ( from.equals(pos_archer_red) ) {
      pos_archer_red = to;
    }
    if (from.equals(blue_unit_pos)) { 
      blue_unit_pos = to;
    }
    if (to.equals(city_pos)) { 
      if(getCityAt(city_pos).getOwner() != getUnitAt(to).getOwner()){
        getCityAt(city_pos).setOwner(getUnitAt(to).getOwner());
        getCityAt(city_pos).changeProduction("ufo");
        getCityAt(city_pos).setWorkforceFocus(GameConstants.foodFocus);
      }
    }
    // notify our observer(s) about the changes on the tiles
    gameObserver.worldChangedAt(from);
    gameObserver.worldChangedAt(to);
    return true; 
  }

  // === Turn handling ===
  private Player inTurn;
  public void endOfTurn() {
    System.out.println( "-- StubGame2 / endOfTurn called." );
    inTurn = (getPlayerInTurn() == Player.RED ?
              Player.BLUE : 
              Player.RED );
    // no age increments
    gameObserver.turnEnds(inTurn, -4000);
  }
  public Player getPlayerInTurn() { return inTurn; }
  

  // === Observer handling ===
  protected GameObserver gameObserver;
  // observer list is only a single one...
  public void addObserver(GameObserver observer) {
    gameObserver = observer;
  } 

  public StubGame2() { 
    defineWorld(1); 
    // AlphaCiv configuration
    pos_archer_red = new Position( 2, 0);
    pos_legion_blue = new Position( 3, 2);
    pos_settler_red = new Position( 4, 3);
    pos_ufo_red = new Position( 6, 4);

    city_pos = new Position(6,6);
    blue_unit_pos = new Position(7,7);

    // the only one I need to store for this stub
    red_archer = new StubUnit( GameConstants.ARCHER, Player.RED );   

    inTurn = Player.RED;
  }

  // A simple implementation to draw the map of DeltaCiv
  protected Map<Position,Tile> world;
  
  private City redCity;
  private Unit blueUnit;

  public Tile getTileAt( Position p ) { return world.get(p); }


  /** define the world.
   * @param worldType 1 gives one layout while all other
   * values provide a second world layout.
   */
  protected void defineWorld(int worldType) {
    world = new HashMap<Position,Tile>();
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        Position p = new Position(r,c);
        world.put( p, new StubTile(GameConstants.PLAINS));
      }
    }
  }

  // TODO: Add more stub behaviour to test MiniDraw updating
  public City getCityAt( Position p ) { 
    if(p.equals(city_pos)){
      return redCity;
    }
    return null;
  }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }  
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}  

  public void setTileFocus(Position position) {
    // TODO: setTileFocus implementation pending.
    //System.out.println("-- StubGame2 / setTileFocus called.");
    //System.out.println(" *** IMPLEMENTATION PENDING ***");
    System.out.println("-- StubGame2 / setTileFocus called at " + position);
    gameObserver.tileFocusChangedAt(position);
  }

  public void createCity(Position position){
    if(position.equals(city_pos)){
      this.redCity = new StubCity(Player.RED);
      gameObserver.worldChangedAt(position);
    }
  }

  //Additional function included for testing functionality
  public void createUnit(Position position){
    if(position.equals(blue_unit_pos)){
      this.blueUnit = new StubUnit(GameConstants.ARCHER, Player.BLUE);
      gameObserver.worldChangedAt(position);
    }
  }

  public void removeCity(Position position){
    if(position.equals(city_pos)){
      this.redCity = null;
      gameObserver.worldChangedAt(position);
    }
  }
  public int getRedAttacks(){return -1;}
  public int getBlueAttacks(){return -1;}
  public void removeUnit(Position position){
    if(position.equals(blue_unit_pos)){
      this.blueUnit = null;
      gameObserver.worldChangedAt(position);
    }
  }
  public void setAttacks(int red, int blue){}

  public void toggleTranscripts(){}

  public TranscriptObserver getTranscript(){return (TranscriptObserver)transcriptObserver;}

  @Override
  public Position getOpenPosition(Position cityLoc) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOpenPosition'");
  }

}

class StubUnit implements  Unit {
  private String type;
  private Player owner;
  public StubUnit(String type, Player owner) {
    this.type = type;
    this.owner = owner;
  }
  public String getTypeString() { return type; }
  public Player getOwner() { return owner; }
  public int getMoveCount() { 
    if(type == "ufo"){
      return 2; 
    }
    return 1;
  }
  public int getDefensiveStrength() { return 0; }
  public int getAttackingStrength() { return 0; }

  public void setDefensiveStrength(int defStr){}
  public void setAttackingStrength(int attStr){}
  public void decreaseMoveCount(){}
  public void resetMoveCount(){}
}

class StubCity implements City {

  private Player owner;
  private int population;
  private int treasury;

  private String focus;

  private String production = GameConstants.ARCHER;

  public StubCity(Player owner){
    this.owner = owner;
    population = 1;
    treasury = 0;
    focus = GameConstants.productionFocus;
  }
  @Override
  public Player getOwner() {
    return this.owner;
  }
  @Override
  public void setOwner(Player owner) {
    this.owner = owner;
  }
  @Override
  public int getSize() {
    return population;
  }
  @Override
  public int getTreasury() {
    return treasury;
  }
  @Override
  public String getProduction() {
    return production;
  }
  @Override
  public void increaseTreasury() {
    this.treasury += 6;
  }
  @Override
  public void decreaseTreasury(int cost) {
    this.treasury -= cost;
  }
  @Override
  public void changeProduction(String type) {
    this.production = type;
  }
  @Override
  public void setTreasury(int value) {
    this.treasury = value;
  }
  @Override
  public void setWorkforceFocus(String balance) {
    this.focus = balance;
  }
  @Override
  public String getWorkforceFocus() {
    return focus;
  }
  
}