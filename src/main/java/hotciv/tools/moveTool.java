package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.DrawingEditor;
import minidraw.standard.SelectionTool;


import java.awt.event.MouseEvent;

public class moveTool extends SelectionTool{
    private Position fromPos;
    private Game game;

    public moveTool(Game game,DrawingEditor editor) {
        super(editor);
        this.game = game;
    }
    @Override
    public void mouseDown(MouseEvent e, int x, int y){
        super.mouseDown(e,x,y);
        //Check if unit
        if (draggedFigure instanceof UnitFigure){
          //Get position of unit
          fromPos = GfxConstants.getPositionFromXY(x,y);
        }
    }
    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseUp(e,x,y);
        //Move unit
        game.moveUnit(fromPos, GfxConstants.getPositionFromXY(x,y));
    }
}
