/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author lguzman
 */
public class Encripta {
    
    /**
     * Funcion que permite realizar la encriptacion en MD5
     * @param txtDato dato a encriptar
     * @return retorna el dato encriptado en MD5
     * @throws java.security.NoSuchAlgorithmException 
     */
    public static String EncriptaMD5(String txtDato) throws NoSuchAlgorithmException{       
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(txtDato.getBytes());

        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        for (int i = 0; i < size; i++) {
            int u = b[i]&255; // unsigned conversion
            if (u<16) {
                h.append("0"+Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString(); 
    }
}
