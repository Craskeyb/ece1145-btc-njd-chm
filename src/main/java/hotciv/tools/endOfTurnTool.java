package hotciv.tools;
import hotciv.framework.Game;
import minidraw.standard.NullTool;


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
