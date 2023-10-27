package hotciv.standard;

import hotciv.framework.*;
import java.util.HashMap;

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
  //private UnitActionStrategy unitStrategy;
  private AgingStrategy ageStrategy;
  private WinningStrategy winStrategy;
  
  public GameImpl(AbstractFactory factory){
    playerInTurn = Player.RED;
    gameAge = 4000;
    mapStrategy = factory.createGameSetup();
    mapStrategy.setUpBoard();
    winStrategy = factory.createWinningStrategy();
    ageStrategy = factory.createAgingStrategy();
    //unitStrategy = createUnitActionStrategy();
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
    return winStrategy.getWinner(gameAge,mapStrategy.getCityMap());
    /*if (this.getAge() == 3000) {
      return Player.RED;
    }
    return null;*/
  }

  @Override
  public int getAge() {
    return gameAge;
  }

  @Override
  public boolean moveUnit(Position from, Position to) {
    // Trying to move another player's units
    if (this.getUnitAt(from).getOwner() != this.getPlayerInTurn()) {
      return false;
    }
    // Trying to move on a mountain
    else if (this.getTileAt(to).getTypeString() == GameConstants.MOUNTAINS) {
      return false;
    }

    //Initiating an attack
    else if(this.getUnitAt(to).getOwner() != this.getPlayerInTurn()){
      mapStrategy.getUnitMap().put(to,this.getUnitAt(from));
      mapStrategy.getUnitMap().remove(from);
      return true;
    }
    
    //Default case, will move the unit from original position to new position
    mapStrategy.getUnitMap().put(to,this.getUnitAt(from));
    mapStrategy.getUnitMap().remove(from);

    return true;
  }

  @Override
  public void endOfTurn() {
    if (this.getPlayerInTurn() == Player.RED) {
      playerInTurn = Player.BLUE;
      City redCity = this.getCityAt(new Position(1, 1));
      redCity.increaseTreasury();

      if(redCity.getTreasury() >= mapStrategy.getUnitCosts().get(redCity.getProduction())){
        redCity.decreaseTreasury(mapStrategy.getUnitCosts().get(redCity.getProduction()));
        Position firstOpen = getOpenPosition(new Position(1,1));
        if(firstOpen.getColumn() != -1 && firstOpen.getRow() != -1){
          mapStrategy.getUnitMap().put(firstOpen, new UnitImpl(Player.RED, redCity.getProduction()));

        }
      }
    } else {
      playerInTurn = Player.RED;
      City blueCity = this.getCityAt(new Position(4, 1));
      blueCity.increaseTreasury();

      if(blueCity.getTreasury() >= mapStrategy.getUnitCosts().get(blueCity.getProduction())){
        blueCity.decreaseTreasury(mapStrategy.getUnitCosts().get(blueCity.getProduction()));
        Position firstOpen = getOpenPosition(new Position(4,1));
        if(firstOpen.getColumn() != -1 && firstOpen.getRow() != -1){
          mapStrategy.getUnitMap().put(firstOpen, new UnitImpl(Player.BLUE, blueCity.getProduction()));

        }
      }
    }
    gameAge = ageStrategy.ageWorld(gameAge);
  }

  @Override
  public void changeWorkForceFocusInCityAt(Position p, String balance) {}

  @Override
  public void changeProductionInCityAt(Position p, String unitType) {
    this.getCityAt(p).changeProduction(unitType);
  }



  public void performUnitActionAt( Position p ) {}



  public Position getOpenPosition(Position cityLoc) {
    Position pos = new Position(-1,-1);
    int cityR = cityLoc.getRow();
    int cityC = cityLoc.getColumn();
    if (this.getUnitAt(cityLoc) == null) {
      return cityLoc;
    } else {
      if (this.getUnitAt(new Position(cityR - 1, cityC)) == null) {
        return new Position(cityR - 1, cityC);
      }
      if(this.getUnitAt(new Position(cityR,cityC+1)) == null){
        return new Position(cityR,cityC+1);
      }
      if(this.getUnitAt(new Position(cityR+1,cityC+1)) == null){
        return new Position(cityR+1,cityC+1);
      }
      if(this.getUnitAt(new Position(cityR+1,cityC)) == null){
        return new Position(cityR+1,cityC);
      }
      if(this.getUnitAt(new Position(cityR+1,cityC-1)) == null){
        return new Position(cityR+1,cityC-1);
      }
      if(this.getUnitAt(new Position(cityR,cityC-1)) == null){
        return new Position(cityR,cityC-1);
      }
      if(this.getUnitAt(new Position(cityR-1,cityC-1)) == null){
        return new Position(cityR-1,cityC-1);
      }
    }
    return pos;
  }


  public void createCity(Position p){
    mapStrategy.getCityMap().put(p,new CityImpl(playerInTurn));
  }
  public void removeUnit(Position p){
    mapStrategy.getUnitMap().remove(p);
  }

}

