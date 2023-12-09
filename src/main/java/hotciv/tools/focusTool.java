package hotciv.tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.SelectionTool;


import java.awt.event.MouseEvent;

public class focusTool extends SelectionTool{
    private Game game;
    public focusTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
    }
    @Override
    public void mouseUp(MouseEvent e, int x, int y){
        super.mouseUp(e,x,y);
        game.setTileFocus(GfxConstants.getPositionFromXY(x,y));
    }

}
