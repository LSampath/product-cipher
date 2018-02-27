/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;

import java.util.Random;

/**
 *
 * @author Lahiru
 */
public class Logic {
    
    public static String permKey;
    public static String subsKey;
    
    private static String ALPHABET = "12345nopqrstuvwxyz 6789O.abcdefghijklmABCDEFGHIJKLMN,\n@67890PQRSTUVWXYZ";
    
    public static String substitutionEcrypt(String text) {
        subsKey = "";
        Random random = new Random();
        while (subsKey.length() < 4) {
            int i = random.nextInt(ALPHABET.length());
            if (ALPHABET.charAt(i) == ' ' || ALPHABET.charAt(i) == '\n' || ALPHABET.charAt(i) == '@') {
                continue;
            } else if (subsKey.indexOf(ALPHABET.charAt(i)) == -1) {
                subsKey += ALPHABET.charAt(i);
            }
        }
        String newAlphabet = subsKey;
        for (int j = 0; j < ALPHABET.length(); j++) {
            if (newAlphabet.indexOf(ALPHABET.charAt(j)) == -1) {
                newAlphabet += ALPHABET.charAt(j);
            }
        }
        String encrypted = "";
        for (int k = 0; k < text.length(); k++) {
            int c = ALPHABET.indexOf(text.charAt(k));
            char sub = newAlphabet.charAt(c);
            encrypted += sub;
        }

        return encrypted;
    }
    
    public static String substitutionDecrypt(String text) {
        String newAlphabet = subsKey;
        for (int j = 0; j < ALPHABET.length(); j++) {
            if (newAlphabet.indexOf(ALPHABET.charAt(j)) == -1) {
                newAlphabet += ALPHABET.charAt(j);
            }
        }
        String decrypted = "";
        for (int k = 0; k < text.length(); k++) {
            int c = newAlphabet.indexOf(text.charAt(k));
            char sub = ALPHABET.charAt(c);
            decrypted += sub;
        }

        return decrypted;
    }
    
    public static String permutationEncrypt(String text) {
        permKey = "";
        Random random = new Random();
        while(permKey.length()<4) {
            String c = String.valueOf(random.nextInt(4));
            if (permKey.indexOf(c) == -1) {
                permKey += c;
            }
        }
        while (text.length()%4 != 0) {
            text += "@";
        } 
        char[] charList = text.toCharArray();
       
        String encrypted = "";
        
        for (int j=0; j<text.length(); j+=4) {
            char[] newPerm = new char[4];
            for (int k=0; k<4; k++) {
                newPerm[k] = text.charAt(j+Integer.parseInt(permKey.charAt(k)+""));
            }
            encrypted += new String(newPerm);
        }
        
        return encrypted;
    }
    
    public static String permutationDecrypt(String text) {
        String decrypted = "";
        for (int j=0; j<text.length(); j+=4) {
            char[] newPerm = new char[4];
            for (int k=0; k<4; k++) {
//                newPerm[Integer.parseInt(permKey.charAt(k)+"")] = text.charAt(j+k);
                newPerm[Integer.parseInt(permKey.charAt(k)+"")] = text.charAt(j+k);
            }
            decrypted += new String(newPerm);
        }
        decrypted = decrypted.replace("@", "");
        
        return decrypted;
    }
}
