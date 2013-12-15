/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.db;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Henri
 */
public class DataHandler {

    private Cipher aes;
    private SecretKeySpec key;
            
    public DataHandler() {
        try {
            initializeHandler();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    public ArrayList<String> encrypt(ArrayList<String> data) {
        try {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> encryptedData = new ArrayList<String>();
        for (String i : data) {
            try {
                encryptedData.add(new String(aes.doFinal(i.getBytes())));
                System.out.println(new String(aes.doFinal(i.getBytes())));
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return encryptedData;
    }
        
    public ArrayList<String> decrypt(ArrayList<String> cryptedData) {
        try {
            aes.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> decryptedData = new ArrayList<String>();
        for (String i : cryptedData) {
            try {
                decryptedData.add(new String(aes.doFinal(i.getBytes())));
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return decryptedData;
    }
    
    private void initializeHandler() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] salt = "jopsadopsadokp sadpo k23412pokpoda ölmfasfjsaio".getBytes();
        int iterations = 10000;
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey tmp = factory.generateSecret(new PBEKeySpec("jilfsjklfsakl2131".toCharArray(), salt, iterations, 128));
        key = new SecretKeySpec(tmp.getEncoded(), "AES");
        aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }
    
}
