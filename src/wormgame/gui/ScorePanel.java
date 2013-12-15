/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import wormgame.game.WormGame;


public class ScorePanel extends JPanel implements Updatable {
    
    private WormGame game;
    
    public ScorePanel(WormGame game) {
        super();
        this.game = game;
    }
    
    private int getScore() {
        return game.getLength();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Score: " + getScore(), game.getLength() - 10, game.getHeight() + 10);
    }

    @Override
    public void update() {
        super.repaint();
    }
    
}
