/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import wormgame.Direction;
import wormgame.domain.Worm;
import wormgame.game.WormGame;

/**
 *
 * @author Henri
 */
public class KeyboardListener implements KeyListener{

    private WormGame wormGame;
    
    public KeyboardListener(WormGame wormGame) {
        this.wormGame = wormGame;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN && wormGame.getWorm().getDirection() != Direction.UP) {
            wormGame.getWorm().setDirection(Direction.DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && wormGame.getWorm().getDirection() != Direction.DOWN) {
            wormGame.getWorm().setDirection(Direction.UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && wormGame.getWorm().getDirection() != Direction.RIGHT) {
            wormGame.getWorm().setDirection(Direction.LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && wormGame.getWorm().getDirection() != Direction.LEFT) {
            wormGame.getWorm().setDirection(Direction.RIGHT);
        }
        if (e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_F2) {
            wormGame.reset();
        }
        if (e.getKeyCode() == KeyEvent.VK_F5) {
            wormGame.save();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
