package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.db.DataBase;
import wormgame.db.Score;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private ArrayList<Updatable> updatable;
    private Worm worm;
    private Apple apple;
    private Random random = new Random();
    private int score = 0;
    private ArrayList<Score> scores = new ArrayList<Score>();
    
    
    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;
        
        this.worm = new Worm(width/2, height/2, Direction.DOWN, this);
        this.apple = newApple(width,height);
        
        addActionListener(this);
        setInitialDelay(2000);

    }


    public boolean continues() {
        return continues;
    }

    public void setUpdatable(ArrayList<Updatable> updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public Worm getWorm() {
        return worm;
    }
    
    public void setWorm(Worm worm) {
        this.worm = worm;
    }
    
    public Apple getApple() {
        return apple;
    }
    
    public void setApple(Apple apple) {
        this.apple = apple;
    }
    
    public int getLength() {
        return worm.getLength();
    }
    
    public int getScore() {
        return score;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            for (Updatable i : updatable) {
                i.update();
            }
            return;
        }
        if (!worm.move() || worm.runsIntoItself()) {
            continues = false;
            saveScore();
        }
        if (worm.runsInto(apple)) {
            worm.grow();
            setApple(newApple(width, height));
            score++;
        }
        for (Updatable i : updatable) {
            i.update();
        }
        super.setDelay(1000 / worm.getLength());
    }
    
    private Apple newApple(int width, int height) {
        Apple newApple = new Apple(random.nextInt(width), random.nextInt(height));
        while (worm.runsInto(newApple)) {
            newApple = new Apple(random.nextInt(width), random.nextInt(height));
        }
        return newApple;
    }

    
    public ArrayList<Score> getScores() {
        return scores;
    }
    
    public void reset() {
        score = 0;
        continues = true;
        worm = new Worm(width/2, height/2, Direction.DOWN, this);
        apple = newApple(width,height);
        setInitialDelay(2000);
    }
    
    private void saveScore() {
        scores.add(new Score(score));
        Collections.sort(scores);
        if (scores.size() > 10) {
            scores.remove(10);
        }
    }

    public void save() {
        DataBase db = new DataBase(this, "databeis.txt", false);
        db.save();
    }
}