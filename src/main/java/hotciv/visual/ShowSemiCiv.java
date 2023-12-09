package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.view.*;
import hotciv.stub.*;
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
