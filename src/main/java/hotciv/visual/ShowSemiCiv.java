package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.tools.compositionTool;

public class ShowSemiCiv {
    
    public static void main(String[] args) {
        Game game = new GameImpl(new SemiCivFactory());

        DrawingEditor editor = 
        new MiniDrawApplication( "Testing functionality with SemiCiv Implementation",  
                               new HotCivFactory4(game) );

        
        editor.open();
        editor.showStatus("Welcome to SemiCiv");

        editor.setTool( new compositionTool(game, editor) );
    }
}   
