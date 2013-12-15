/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.db;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Henri
 */
public class Score implements Comparable<Score>{
    
    private int score;
    private Calendar date;;
    
    public Score(int score) {
        this.score = score;
        this.date = new GregorianCalendar();
    }
    
    public Score(int score, int year,
                 int month,
                 int dayOfMonth,
                 int hourOfDay,
                 int minute) {
        this.score = score;
        this.date = new GregorianCalendar(year, month, dayOfMonth, 
                    hourOfDay, minute);
    }
    
    public String[] getDataChar() {
        String[] data = new String[2];
        data[0] = "" + score;
        data[1] = date.toString();
        return data;
    }
    
    
    public String getData() {
        return score + " " + date.get(Calendar.YEAR) + " " + date.get(Calendar.MONTH) + " " + 
                date.get(Calendar.DAY_OF_MONTH) + " " + date.get(Calendar.HOUR_OF_DAY) + " " + 
                date.get(Calendar.MINUTE) + " " + date.get(Calendar.SECOND);
    }

    @Override
    public int compareTo(Score o) {
        if (o.score == this.score) {
            return o.date.compareTo(this.date);
        }
        else {
            return o.score - this.score;
        }
    }

    @Override
    public String toString() {
        return "Score{" + "score=" + score + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.score;
        hash = 97 * hash + (this.date != null ? this.date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Score other = (Score) obj;
        if (this.score != other.score) {
            return false;
        }
        if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
            return false;
        }
        return true;
    }
    
    
}
