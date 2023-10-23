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
  private HashMap<Position,Tile> map = new HashMap<Position, Tile>();
  private HashMap<Position,Unit> unitMap = new HashMap<Position, Unit>();
  private HashMap<Position,City> cityMap = new HashMap<Position, City>();
  private HashMap<String,Integer> unitCosts = new HashMap<String,Integer>();
  private Player playerInTurn;
  private int gameAge;
  
  public GameImpl(){

    playerInTurn = Player.RED;
    gameAge = 4000;

    unitCosts.put(GameConstants.ARCHER,10);
    unitCosts.put(GameConstants.LEGION,15);
    unitCosts.put(GameConstants.SETTLER,30);

    for(int i=0;i<=GameConstants.WORLDSIZE;i++){
      for(int j=0;j<=GameConstants.WORLDSIZE;j++){
        if (i == 1 && j == 0){
          map.put(new Position(i,j),new TileImpl(GameConstants.OCEANS));
        }
        else if (i == 0 && j == 1){
          map.put(new Position(i,j),new TileImpl(GameConstants.HILLS));
        }
        else if (i == 2 && j == 2){
          map.put(new Position(i,j),new TileImpl(GameConstants.MOUNTAINS));
        }
        else {
          map.put(new Position(i,j),new TileImpl(GameConstants.PLAINS));
        }
        if (i == 2 && j == 0){
          unitMap.put(new Position(i,j),new UnitImpl(Player.RED, GameConstants.ARCHER));
        }
        else if (i == 3 && j == 2){
          unitMap.put(new Position(i,j),new UnitImpl(Player.BLUE, GameConstants.LEGION));
        }
        else if (i == 4 && j == 3){
          unitMap.put(new Position(i,j),new UnitImpl(Player.RED, GameConstants.SETTLER));
        }
        if (i == 1 && j == 1){
          cityMap.put(new Position(i,j),new CityImpl(Player.RED));
        }
        else if (i == 4 && j == 1){
          cityMap.put(new Position(i,j),new CityImpl(Player.BLUE));
        }
      }
    }
  }

  public Tile getTileAt( Position p ) { return map.get(p);}
  public Unit getUnitAt( Position p ) { return unitMap.get(p); }
  public City getCityAt( Position p ) { return cityMap.get(p); }

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
      unitMap.put(to,this.getUnitAt(from));
      unitMap.remove(from);
      return true;
    }
    
    //Default case, will move the unit from original position to new position
    unitMap.put(to,this.getUnitAt(from));
    unitMap.remove(from);
    return true;
  }


  public void endOfTurn() {
    if(this.getPlayerInTurn() == Player.RED){
      playerInTurn = Player.BLUE;
      City redCity = this.getCityAt(new Position(1,1));
      redCity.increaseTreasury();
      if(redCity.getTreasury() >= unitCosts.get(redCity.getProduction())){
        redCity.decreaseTreasury(unitCosts.get(redCity.getProduction()));
        Position firstOpen = getOpenPosition(new Position(1,1));
        if(firstOpen.getColumn() != -1 && firstOpen.getRow() != -1){
          unitMap.put(firstOpen, new UnitImpl(Player.RED, redCity.getProduction()));
        }
      }
    }
    else{
      playerInTurn = Player.RED;
      City blueCity = this.getCityAt(new Position(4,1));
      blueCity.increaseTreasury();
      if(blueCity.getTreasury() >= unitCosts.get(blueCity.getProduction())){
        blueCity.decreaseTreasury(unitCosts.get(blueCity.getProduction()));
        Position firstOpen = getOpenPosition(new Position(4,1));
        if(firstOpen.getColumn() != -1 && firstOpen.getRow() != -1){
          unitMap.put(firstOpen, new UnitImpl(Player.BLUE, blueCity.getProduction()));
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
    cityMap.put(p,new CityImpl(playerInTurn));
  }
  public void removeUnit(Position p){
    unitMap.remove(p);
  }

}

