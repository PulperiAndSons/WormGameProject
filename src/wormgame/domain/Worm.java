/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import java.util.ArrayList;
import java.util.List;
import wormgame.Direction;
import wormgame.game.WormGame;

/**
 *
 * @author Henri
 */
public class Worm {
    private Direction direction, newDirection;
    private List<Piece> pieceList = new ArrayList<Piece>();
    private boolean grow = false;
    private int xLimit, yLimit;
    
    public Worm(int originalX, int originalY, Direction originalDirection, WormGame wormGame) {
        this.xLimit = wormGame.getWidth();
        this.yLimit = wormGame.getHeight();
        this.direction = originalDirection;
        pieceList.add(new Piece(originalX, originalY));
    }
    
    //public Direction getDirection() return's Worm's direction.
    public Direction getDirection() {
        return direction;
    }
    
    //public void setDirection(Direction dir) sets a new direction for the worm. The worm starts to move in the new direction when the method move is called again.
    public void setDirection(Direction dir) {
        newDirection = dir;
    }
    
    //public int getLength() returns the Worm's length. The Worm's length has to be equal to the length of the list returned by the method getPieces().
    public int getLength() {
        return pieceList.size();
    }
    
    //public List<Piece> getPieces() returns a list of Piece objects which the worm is composed of. The pieces in the list are in order, with the worm head at the end of the list.
    public List<Piece> getPieces() {
        return pieceList;
    }
    
    //public void move() moves the worm one piece forward.
    public boolean move() {
        if (newDirection != null) {
            direction = newDirection;
        }
        if (runningToWall()) {
            return false;
        }
        Piece head = pieceList.get(pieceList.size()-1);
        if (direction == Direction.UP) {
            pieceList.add(new Piece(head.getX(), head.getY()-1));
        }
        else if (direction == Direction.DOWN) {
            pieceList.add(new Piece(head.getX(), head.getY()+1));
        }
        else if (direction == Direction.LEFT) {
            pieceList.add(new Piece(head.getX()-1, head.getY()));
        }
        else if (direction == Direction.RIGHT) {
            pieceList.add(new Piece(head.getX()+1, head.getY()));
        }

        if (grow || pieceList.size() <= 3) {
            grow = false;
        }
        else {
            pieceList.remove(0);
        }
        //System.out.println(direction.toString() + pieceList + " > length: " + this.getLength());
        return true;
    }
    
    
    //public void grow() grows the worm by one piece. The worm grows together with the following move method call; after the first move method call the worm does not grow any more.
    public void grow() {
        grow = true;
    }
    
    //public boolean runsInto(Piece piece) checks whether the worm runs into the parameter piece. If so -- that is, if a piece of the worm runs into the parameter piece -- the method returns the value true; otherwise it returns false.
    public boolean runsInto(Piece piece) {
        if (piece == null) {
            return false;
        }
        // check if piece inside worm
        for (Piece i : pieceList) {
            if (piece.runsInto(i)) {
                return true;
            }
        }
        return false;
    }
    
    //public boolean runsIntoItself() check whether the worm runs into itself. If so -- that is, if one of the worm's pieces runs into another piece -- the method returns the value true. Otherwise it returns false.
    public boolean runsIntoItself() {
        if (pieceList.size() == 1) {
            return false;
        }
        Piece head = pieceList.get(pieceList.size()-1);
        for (int i = 0; i < pieceList.size()-1; i++) {
            if (head.runsInto(pieceList.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    private boolean runningToWall() {
        Piece head = this.getPieces().get(this.getLength()-1);
        if (head.getX() - 1 < 0 && this.getDirection() == Direction.LEFT) {
            return true;
        }
        if (head.getX() + 1 >= xLimit && this.getDirection() == Direction.RIGHT) {
            return true;
        }
        if (head.getY() - 1 < 0 && this.getDirection() == Direction.UP) {
            return true;
        }
        if (head.getY() + 1 >= yLimit && this.getDirection() == Direction.DOWN) {
            return true;
        }
        return false;
    }

    /*
    @Override
    public String toString() {
        return direction.toString() + pieceList;
    }*/
}
