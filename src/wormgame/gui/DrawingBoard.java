/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import wormgame.domain.Piece;
import wormgame.game.WormGame;

/**
 *
 * @author Henri
 */
public class DrawingBoard extends JPanel implements Updatable{
    private WormGame wormGame;
    private int pieceLength;
    
    public DrawingBoard(WormGame wormGame, int pieceLength) {
        super();
        this.wormGame = wormGame;
        this.pieceLength = pieceLength;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Piece i : wormGame.getWorm().getPieces()) {
            g.setColor(Color.black);
            g.fill3DRect(i.getX()*pieceLength, i.getY()*pieceLength, pieceLength, pieceLength, true);
        }
        g.setColor(Color.red);
        g.fillOval(wormGame.getApple().getX()*pieceLength, wormGame.getApple().getY()*pieceLength, pieceLength, pieceLength);
        g.drawLine(0, 0, 0, wormGame.getHeight()*pieceLength);
        g.drawLine(0, 0, wormGame.getWidth()*pieceLength, 0);
        g.drawLine(wormGame.getWidth()*pieceLength, 0, wormGame.getWidth()*pieceLength, wormGame.getHeight()*pieceLength);
        g.drawLine(0, wormGame.getHeight()*pieceLength, wormGame.getWidth()*pieceLength, wormGame.getHeight()*pieceLength);
        g.drawString("Score: " + wormGame.getScore(), (wormGame.getWidth()-5)*pieceLength, wormGame.getHeight()*pieceLength + 10);
        if (!wormGame.continues()) {
            g.drawString("GAME OVER XXXX", (wormGame.getWidth()-5)*pieceLength, wormGame.getHeight()*pieceLength + 20);
        }
    }

    @Override
    public void update() {
        super.repaint();
    }
}
