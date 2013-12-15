package wormgame.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import wormgame.game.WormGame;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WormGame game;
    private int sideLength;
    private ArrayList<Updatable> updatable = new ArrayList<Updatable>();
    
    public UserInterface(WormGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
    }

    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        int width = (game.getWidth()+1)*sideLength+20;
        int height = (game.getHeight()+2)*sideLength+50;

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        // Create drawing board first which then is added into container-object.
        // After this, create keyboard listener which is added into frame-object
        
        
        DrawingBoard drawingBoard = new DrawingBoard(game, sideLength);
        updatable.add(drawingBoard);
        
        container.setLayout(new BorderLayout());
        container.add(createTitleBar(), BorderLayout.NORTH);
        container.add(drawingBoard);
        container.add(createTitleBar(), BorderLayout.SOUTH);
        
        frame.addKeyListener(new KeyboardListener(game));
    }

    private JPanel createTitleBar() {
        JPanel titleBar = new JPanel();
        titleBar.setLayout(new GridLayout(1,3));
        titleBar.add(new JLabel("Worm game"));
        titleBar.add(new JLabel("Build: 0.01a"));
        return titleBar;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ArrayList<Updatable> getUpdatable() {
        return updatable;
    }
}