package hotciv.standard;

import hotciv.framework.*;
import java.lang.Math;

/**
 * This is the hotfix for release 2.1
 *
 * Skeleton implementation of HotCiv.
 *
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 *
 * Please visit http://www.baerbak.com/ for further information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {


  private Player playerInTurn;
  private int gameAge;
  private GameSetupStrategy mapStrategy;
  private UnitActionStrategy unitStrategy;
  private AgingStrategy ageStrategy;
  private WinningStrategy winStrategy;
  private AttackStrategy attackStrategy;
  private ProductionChangeStrategy productionChangeStrategy;
  
  //Transcript observer and CivDrawing/GameObserver object
  private Observer transcriptObserver;
  private GameObserver gameObserver;
  
  //private WorkforceStrategy workforceStrategy;
  private int redAttacks = 0;
  private int blueAttacks = 0;
  private Boolean transcriptMode = false;
  private Boolean observerMode = false;
  
  public GameImpl(AbstractFactory factory){
    playerInTurn = Player.RED;
    gameAge = -4000;
    mapStrategy = factory.createGameSetup();
    mapStrategy.setUpBoard();
    winStrategy = factory.createWinningStrategy();
    ageStrategy = factory.createAgingStrategy();
    unitStrategy = factory.createUnitActionStrategy();
    attackStrategy = factory.createAttackStrategy();
    productionChangeStrategy = factory.createProductionChangeStrategy();
    //workforceStrategy = factory.createWorkforceStrategy();
    transcriptObserver = new TranscriptObserver();

  }


  public Tile getTileAt( Position p ) { 
    return mapStrategy.getTileMap().get(p);
  }

  public Unit getUnitAt( Position p ) { 
    return  mapStrategy.getUnitMap().get(p);
  }

  public City getCityAt( Position p ) { 
    return  mapStrategy.getCityMap().get(p);
  }

  @Override
  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  @Override
  public Player getWinner() {
    return winStrategy.getWinner(gameAge,mapStrategy.getCityMap(), this);
  }

  @Override
  public int getAge() {
    return gameAge;
  }

  @Override
  public boolean moveUnit(Position from, Position to) {

    String unitType = this.getUnitAt(from).getTypeString();

    // Trying to move another player's units
    if (this.getUnitAt(from).getOwner() != this.getPlayerInTurn()) {
      return false;
    }
    if(this.getUnitAt(from).getMoveCount() == 0){
      return false;
    }
    //Trying to move onto your own units
    if (this.getUnitAt(to) != null && this.getUnitAt(to).getOwner() == this.getPlayerInTurn()){
      return false;
    }
    //Trying to move more than one tile length away
    if(Math.abs(to.getColumn()-from.getColumn()) > 1 || Math.abs(to.getRow()-from.getRow())>1){
      return false;
    }
    // Trying to move on a mountain or ocean
    if ((this.getTileAt(to).getTypeString() == GameConstants.MOUNTAINS || this.getTileAt(to).getTypeString() == GameConstants.OCEANS) && (unitType == GameConstants.ARCHER || unitType == GameConstants.LEGION || unitType == GameConstants.SETTLER)) {
      return false;
    }
    //Moving Out of bounds
    if (to.getRow() > 15 || to.getColumn() > 15){
      return false;
    }
    if (to.getRow() < 0 || to.getColumn() < 0){
      return false;
    }
    //Conquering City
    if (this.getCityAt(to) != null && !(this.getCityAt(to).getOwner() == this.getPlayerInTurn())){
      //Check if there is an attack happening in the city, and the result determines whether or not its conquered
      if(this.getUnitAt(to) != null && this.getUnitAt(to).getOwner() != this.getPlayerInTurn()){
        Boolean attackResult = attackStrategy.decideAttack(this,mapStrategy,to,from);
        if(attackResult == true){
          this.getUnitAt(to).decreaseMoveCount();
          this.getCityAt(to).setOwner(this.getPlayerInTurn());
        }
        if(observerMode == true) {
          gameObserver.worldChangedAt(from);
          gameObserver.worldChangedAt(to);
        }
        return attackResult;
      }
      else{
        this.getCityAt(to).setOwner(this.getPlayerInTurn());
      }
    }
    //Initiating an attack
    if(this.getUnitAt(to) != null && this.getUnitAt(to).getOwner() != this.getPlayerInTurn()){
      Boolean attackResult = attackStrategy.decideAttack(this,mapStrategy,to,from);
      if(attackResult == true){
        this.getUnitAt(to).decreaseMoveCount();
      }
      if(observerMode == true) {
          gameObserver.worldChangedAt(from);
          gameObserver.worldChangedAt(to);
      }
      return attackResult;
    }
    else{
      //Default case, will move the unit from original position to new position
      mapStrategy.getUnitMap().put(to,this.getUnitAt(from));
      mapStrategy.getUnitMap().remove(from);
      this.getUnitAt(to).decreaseMoveCount();
    }



    //Transcript for a successful move
    if(transcriptMode == true){
      String transcription = "Player " + getPlayerInTurn() + " moves " + getUnitAt(to).getTypeString() + " at (" + from.getRow() + "," + from.getColumn() + ") to (" + to.getRow() + "," + to.getColumn() + ")."; 
      transcriptObserver.update(transcription);
    }
    if(observerMode == true){
      gameObserver.worldChangedAt(from);
      gameObserver.worldChangedAt(to);
    }
    return true;
  }

  @Override
  public void endOfTurn() {
    if (this.getPlayerInTurn() == Player.RED) {
      for(int i=0;i<GameConstants.WORLDSIZE; i++){
        for (int j=0; j<GameConstants.WORLDSIZE; j++) {
          if (this.getCityAt(new Position(i, j)) != null){
            Player ownerOfCity = this.getCityAt(new Position(i, j)).getOwner();
          if (ownerOfCity == Player.RED) {
            City redCity = this.getCityAt(new Position(i, j));
            redCity.increaseTreasury();
            if (redCity.getTreasury() >= mapStrategy.getUnitCosts().get(redCity.getProduction())) {
              redCity.decreaseTreasury(mapStrategy.getUnitCosts().get(redCity.getProduction()));
              Position firstOpen = getOpenPosition(new Position(i, j));
              if (firstOpen.getColumn() != -1 && firstOpen.getRow() != -1) {
                mapStrategy.getUnitMap().put(firstOpen, new UnitImpl(Player.RED, redCity.getProduction()));
                if(observerMode == true) {
                  gameObserver.worldChangedAt(firstOpen);
                }
              }
            }
          }
        }
        }
      }

      //Transcript for if red ends their turn
      if(transcriptMode == true){
        String transcription = "Player " + getPlayerInTurn() + " ends turn."; 
        transcriptObserver.update(transcription);
      }
      playerInTurn = Player.BLUE;

    } else {
      for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
        for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
          if (this.getCityAt(new Position(i, j)) != null) {
            Player ownerOfCity = this.getCityAt(new Position(i, j)).getOwner();
            if (ownerOfCity == Player.BLUE) {
              City blueCity = this.getCityAt(new Position(i, j));
              blueCity.increaseTreasury();
              if (blueCity.getTreasury() >= mapStrategy.getUnitCosts().get(blueCity.getProduction())) {
                blueCity.decreaseTreasury(mapStrategy.getUnitCosts().get(blueCity.getProduction()));
                Position firstOpen = getOpenPosition(new Position(i, j));
                if (firstOpen.getColumn() != -1 && firstOpen.getRow() != -1) {
                  mapStrategy.getUnitMap().put(firstOpen, new UnitImpl(Player.BLUE, blueCity.getProduction()));
                  if(observerMode == true) {
                    gameObserver.worldChangedAt(firstOpen);
                  }
                }
              }
            }
          }
        }
      }

      //Transcript for when blue ends their turn
      if(transcriptMode == true){
          String transcription = "Player " + getPlayerInTurn() + " ends turn."; 
          transcriptObserver.update(transcription);
        }
      playerInTurn = Player.RED;

    }
    gameAge = ageStrategy.ageWorld(gameAge);
    
    //Resetting move counts
    for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
        for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
          if(this.getUnitAt(new Position(i,j)) != null){
            this.getUnitAt(new Position(i,j)).resetMoveCount();
          }
        }
      }
    if(observerMode == true) {
      gameObserver.turnEnds(playerInTurn, gameAge);
    }

  }

  @Override
  public void changeWorkForceFocusInCityAt(Position p, String balance) {
      //Transcription for workforce focus change
      if(transcriptMode == true){
        String transcription = "Player " + getPlayerInTurn() + " changes work force focus in city at (" + p.getRow() + "," + p.getColumn() + ") to " + balance; 
        transcriptObserver.update(transcription);
      }
  }

  @Override
  public void changeProductionInCityAt(Position p, String unitType) {
    productionChangeStrategy.changeProduction(this, p, unitType);

    //Transcription for production change
    if(transcriptMode == true){
      String transcription = "Player " + getPlayerInTurn() + " changes production in city at (" + p.getRow() + "," + p.getColumn() + ") to " + unitType + "."; 
      transcriptObserver.update(transcription);
    }
  }

  public void performUnitActionAt( Position p ) {
    unitStrategy.performUnitActionAt(p, mapStrategy.getUnitMap(), this);

    //Transcription for workforce focus change
    if(transcriptMode == true){
      String transcription = "Player " + getPlayerInTurn() + " performs unit action at (" + p.getRow() + "," + p.getColumn() + ") for " + getUnitAt(p).getTypeString() + " unit."; 
      transcriptObserver.update(transcription);
    }
  }

  public Position getOpenPosition(Position cityLoc) {
    Position pos = new Position(-1,-1);
    int cityR = cityLoc.getRow();
    int cityC = cityLoc.getColumn();
    if (this.getUnitAt(cityLoc) == null && !this.getTileAt(cityLoc).getTypeString().equals("mountain")) {
      return cityLoc;
    } else {
      if (this.getUnitAt(new Position(cityR - 1, cityC)) == null && !this.getTileAt(new Position(cityR - 1, cityC)).getTypeString().equals("mountain")) {
        return new Position(cityR - 1, cityC);
      }
      if(this.getUnitAt(new Position(cityR,cityC+1)) == null && !this.getTileAt(new Position(cityR , cityC+1)).getTypeString().equals("mountain")){
        return new Position(cityR,cityC+1);
      }
      if(this.getUnitAt(new Position(cityR+1,cityC+1)) == null && !this.getTileAt(new Position(cityR + 1, cityC+1)).getTypeString().equals("mountain")){
        return new Position(cityR+1,cityC+1);
      }
      if(this.getUnitAt(new Position(cityR+1,cityC)) == null && !this.getTileAt(new Position(cityR + 1, cityC)).getTypeString().equals("mountain")){
        return new Position(cityR+1,cityC);
      }
      if(this.getUnitAt(new Position(cityR+1,cityC-1)) == null && !this.getTileAt(new Position(cityR + 1, cityC-1)).getTypeString().equals("mountain")){
        return new Position(cityR+1,cityC-1);
      }
      if(this.getUnitAt(new Position(cityR,cityC-1)) == null && !this.getTileAt(new Position(cityR, cityC - 1)).getTypeString().equals("mountain")){
        return new Position(cityR,cityC-1);
      }
      if(this.getUnitAt(new Position(cityR-1,cityC-1)) == null && !this.getTileAt(new Position(cityR - 1, cityC-1)).getTypeString().equals("mountain")){
        return new Position(cityR-1,cityC-1);
      }
      if(this.getUnitAt(new Position(cityR-1,cityC+1)) == null && !this.getTileAt(new Position(cityR - 1, cityC+1)).getTypeString().equals("mountain")){
        return new Position(cityR-1,cityC+1);
      }
    }
    return pos;
  }


  public void createCity(Position p){
    mapStrategy.getCityMap().put(p,new CityImpl(playerInTurn));
    if(observerMode == true){
      gameObserver.worldChangedAt(p);
    }
  }
  public void removeUnit(Position p){
    mapStrategy.getUnitMap().remove(p);
    if(observerMode == true){
      gameObserver.worldChangedAt(p);
    }
  }
  public void removeCity(Position p){
    mapStrategy.getCityMap().remove(p);
    if(observerMode == true){
      gameObserver.worldChangedAt(p);
    }
  }

  public int getRedAttacks(){
    return redAttacks;
  }
  public int getBlueAttacks(){
    return blueAttacks;
  }
  public void setAttacks(int red, int blue){ 
    redAttacks = red; blueAttacks = blue;
  }

  public void toggleTranscripts(){
    if(transcriptMode == false){
      transcriptMode = true;
    }
    else{
      transcriptMode = false;
    }
  }

  public TranscriptObserver getTranscript(){
    return (TranscriptObserver)transcriptObserver;
  }

  public void addObserver(GameObserver observer){
    this.gameObserver = observer;
    this.observerMode = true;
  }
  public void setTileFocus(Position position){
    this.gameObserver.tileFocusChangedAt(position);
  }
}

