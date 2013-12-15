package wormgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import javax.swing.SwingUtilities;
import wormgame.db.Score;
import wormgame.gui.UserInterface;
import wormgame.game.WormGame;

public class Main {

    public static void main(String[] args) {
        runGame();
    }
    
    public static void scoreTest() {
        ArrayList<Score> scoreList = new ArrayList<Score>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            scoreList.add(new Score(random.nextInt(100)));
        }
        
        Collections.sort(scoreList);
        for (Score i : scoreList) {
            System.out.println(i.getData());
        }
    }
    
    public static void runGame() {
        WormGame game = new WormGame(20, 20);

        UserInterface ui = new UserInterface(game, 20);
        SwingUtilities.invokeLater(ui);

        while (ui.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("The drawing board hasn't been created yet.");
            }
        }

        game.setUpdatable(ui.getUpdatable());
        game.start();
    }

}
