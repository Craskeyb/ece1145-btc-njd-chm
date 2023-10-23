package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/* This is the hotfix for release 2.1 */

/** Skeleton implementation of HotCiv.
 
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

public class GameImpl implements Game {
  private Player playerInTurn;
  private int gameAge;
  private GameSetupStrategy map;
  
  public GameImpl(){
    playerInTurn = Player.RED;
    gameAge = 4000;
    map = new MapImpl();
    map.setUpBoard();
  }

  public Tile getTileAt( Position p ) { return map.getTileMap().get(p); }
  public Unit getUnitAt( Position p ) { return  map.getUnitMap().get(p); }
  public City getCityAt( Position p ) { return  map.getCityMap().get(p); }

  public Player getPlayerInTurn() { return playerInTurn; }
  
  public Player getWinner() { 
    if(this.getAge() == 3000){
      return Player.RED;
    }
    return null; 
  }
  
  public int getAge() { return gameAge;}
  

  public boolean moveUnit( Position from, Position to ) {
    
    //Trying to move another player's units
    if(this.getUnitAt(from).getOwner() != this.getPlayerInTurn()){
      return false;
    }
    //Trying to move on a mountain
    else if(this.getTileAt(to).getTypeString() == GameConstants.MOUNTAINS){
      return false;
    }
    //Initiating an attack
    else if(this.getUnitAt(to).getOwner() != this.getPlayerInTurn()){
      map.getUnitMap().put(to,this.getUnitAt(from));
      map.getUnitMap().remove(from);
      return true;
    }
    
    //Default case, will move the unit from original position to new position
    map.getUnitMap().put(to,this.getUnitAt(from));
    map.getUnitMap().remove(from);
    return true;
  }


  public void endOfTurn() {
    if(this.getPlayerInTurn() == Player.RED){
      playerInTurn = Player.BLUE;
      City redCity = this.getCityAt(new Position(1,1));
      redCity.increaseTreasury();
      if(redCity.getTreasury() >= map.getUnitCosts().get(redCity.getProduction())){
        redCity.decreaseTreasury(map.getUnitCosts().get(redCity.getProduction()));
        Position firstOpen = getOpenPosition(new Position(1,1));
        if(firstOpen.getColumn() != -1 && firstOpen.getRow() != -1){
          map.getUnitMap().put(firstOpen, new UnitImpl(Player.RED, redCity.getProduction()));
        }
      }
    }
    else{
      playerInTurn = Player.RED;
      City blueCity = this.getCityAt(new Position(4,1));
      blueCity.increaseTreasury();
      if(blueCity.getTreasury() >= map.getUnitCosts().get(blueCity.getProduction())){
        blueCity.decreaseTreasury(map.getUnitCosts().get(blueCity.getProduction()));
        Position firstOpen = getOpenPosition(new Position(4,1));
        if(firstOpen.getColumn() != -1 && firstOpen.getRow() != -1){
          map.getUnitMap().put(firstOpen, new UnitImpl(Player.BLUE, blueCity.getProduction()));
        }
      }
    }
    gameAge -= 100;
  }


  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

  public void changeProductionInCityAt( Position p, String unitType ) {
    this.getCityAt(p).changeProduction(unitType);
  }


  public void performUnitActionAt( Position p ) {}



  public Position getOpenPosition(Position cityLoc){
    Position pos = new Position(-1,-1);
    int cityR = cityLoc.getRow();
    int cityC = cityLoc.getColumn();
    if(this.getUnitAt(cityLoc) == null){
      return cityLoc;
    }
    else{
      if(this.getUnitAt(new Position(cityR-1,cityC)) == null){
        return new Position(cityR-1,cityC);
      }
      if(this.getUnitAt(new Position(cityR-1,cityC+1)) == null){
        return new Position(cityR-1,cityC+1);
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
    map.getCityMap().put(p,new CityImpl(playerInTurn));
  }
  public void removeUnit(Position p){
    map.getUnitMap().remove(p);
  }

}
