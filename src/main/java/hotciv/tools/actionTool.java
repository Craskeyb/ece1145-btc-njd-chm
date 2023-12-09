package hotciv.tools;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.SelectionTool;


import java.awt.event.MouseEvent;

public class actionTool extends SelectionTool{
    private Game game;
    private Unit unit;
    public actionTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
    }
    @Override
    public void mouseUp(MouseEvent e, int x, int y){
        //See if shift is down
        boolean shiftIsDown = e.isShiftDown();
        Player currentPlayer = game.getPlayerInTurn();
        unit = game.getUnitAt(GfxConstants.getPositionFromXY(x,y));
        if(shiftIsDown && unit.getOwner() == currentPlayer){
            game.performUnitActionAt(GfxConstants.getPositionFromXY(x,y));
            String unitType = unit.getTypeString();
            System.out.println("Unit " + unitType + " Perfomed action at: " + GfxConstants.getPositionFromXY(x,y));
        }

    }

}
