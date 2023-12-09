package hotciv.tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;


import java.awt.event.MouseEvent;

public class focusTool extends NullTool{
    private Game game;
    private DrawingEditor editor;

    public focusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override

    public void mouseUp(MouseEvent e, int x, int y){

        game.setTileFocus(GfxConstants.getPositionFromXY(x,y));
    }

}
