/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import wormgame.game.WormGame;

/**
 *
 * @author Henri
 */
public class DataBase {
    
    private DataHandler dh = new DataHandler();
    private String fileName;
    private boolean crypted;
    private WormGame game;
    
    public DataBase(WormGame game, String fileName, boolean crypted) {
        this.fileName = fileName;
        this.game = game;
        this.crypted = crypted;
    }
    
    public void save() {
        ArrayList<Score> gameData = new ArrayList<Score>();
        ArrayList<Score> oldData;
        try {
            oldData = oldData();
        } catch (FileNotFoundException ex) {
            oldData = new ArrayList<Score>();
        }
        for (Score i : oldData) {
            if (!gameData.contains(i)) {
                gameData.add(i);
            }
        }
        System.out.println(gameData.size() + " unique added");
        System.out.println(game.getScores().size() + " new scores recieved");
        for (Score i : game.getScores()) {
            if (!gameData.contains(i)) {
                gameData.add(i);
            }
        }
        System.out.println(gameData.size() + " total scores");
        Collections.sort(gameData);
        
        ArrayList<String> dataStrings = new ArrayList<String>();
        int index = gameData.size();
        if (index > 15 ) {
            index = 15;
        }
        for (Score i : gameData.subList(0, index)) {
            dataStrings.add(i.getData());
        }
        
        if (crypted) {
            dataStrings = dh.encrypt(dataStrings);
        }
        saveToFile(dataStrings);
    }
    
    private ArrayList<Score> oldData() throws FileNotFoundException {
        ArrayList<Score> oldData = new ArrayList<Score>();
        Scanner reader = new Scanner(new File(fileName));
        if (reader != null) {
            while(reader.hasNext()) {
                String line = reader.nextLine();
                if (!line.isEmpty()) {
                    String[] parsedLine = line.split(" ");
                    oldData.add(new Score(Integer.parseInt(parsedLine[0]), Integer.parseInt(parsedLine[1]), 
                                Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[3]), 
                                Integer.parseInt(parsedLine[4]), Integer.parseInt(parsedLine[5])));
                }
            }
        }
        reader.close();
        System.out.println(oldData.size() + " old scores found in file");
        return oldData;
    }

    private void saveToFile(ArrayList<String> gameData) {
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
            for (String i : gameData) {
                writer.write(i + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
}
