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


public class TitlePanel extends JPanel implements Updatable {
    
    private JLabel title;
    private WormGame game;
    
    public TitlePanel(WormGame game) {
        super();
        this.game = game;
        this.title = new JLabel("Worm game :3");
        super.setLayout(new GridLayout(1,3));
        super.add(title, 1);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void update() {
        super.repaint();
    }
    
}
