/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

/**
 *
 * @author Henri
 */
public class Piece {
    private int x,y;
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean runsInto(Piece piece) {
        return x == piece.x && y == piece.y;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
