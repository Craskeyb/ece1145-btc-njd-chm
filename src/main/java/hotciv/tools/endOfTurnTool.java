package hotciv.tools;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;
import minidraw.standard.handlers.DragTracker;

import java.awt.event.MouseEvent;

public class endOfTurnTool extends NullTool{
    
    private Game game;


    public endOfTurnTool(Game game) {
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y){
        if(560 < x && x < 587 && 60 < y && y < 104){
            game.endOfTurn();
        }
    }
}
