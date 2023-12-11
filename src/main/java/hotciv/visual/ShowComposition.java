package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;



import hotciv.framework.*;

import hotciv.stub.*;
import hotciv.tools.*;

/** Template code for exercise FRS 36.44.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowComposition {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click and/or drag any item to see all game actions",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click and drag any item to see Game's proper response.");
    game.createCity(new Position(6,6));
    // TODO: Replace the setting of the tool with your CompositionTool implementation.
    editor.setTool( new compositionTool(game, editor) );
  }
}
