package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;


import java.awt.event.MouseEvent;


public class compositionTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private actionTool aTool;
    private endOfTurnTool eTool;
    private moveTool mTool;
    private focusTool fTool;

    private Position startPos;

    public compositionTool(Game game, DrawingEditor editor){
        this.game = game;
        this.editor = editor;
        this.aTool = new actionTool(game);
        this.eTool = new endOfTurnTool(game);
        this.mTool = new moveTool(game, editor);
        this.fTool = new focusTool(game);
    }

    public void mouseDown(MouseEvent e, int x, int y){
        Position pos = GfxConstants.getPositionFromXY(x, y);
        startPos = pos;
        
        if(560 < x && x < 587 && 60 < y && y < 104){
            eTool.mouseDown(e,x,y);
        }
        else if(game.getUnitAt(pos) != null){
            mTool.mouseDown(e,x,y);
        }
    }

    public void mouseUp(MouseEvent e, int x, int y){
        if(e.isShiftDown()){
            aTool.mouseUp(e, x, y);
        }
        else{
            Position endPos = GfxConstants.getPositionFromXY(x, y);
            if(endPos.equals(startPos)){
                fTool.mouseUp(e, x, y);
            }
            else{
                if(game.getUnitAt(startPos) != null){
                    mTool.mouseUp(e, x, y);
                }
            }
        }
    }
}
