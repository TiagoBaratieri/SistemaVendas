/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author tiago
 */
public class Hash {

    public static String toMD5(String senha) throws NoSuchAlgorithmException {
        String md5 = null;

        if (senha == null) {
            return null;
        }

        MessageDigest digest;

        digest = MessageDigest.getInstance("md5");
        digest.update(senha.getBytes(), 0, senha.length());
        md5 = new BigInteger(1, digest.digest()).toString(16);

        return md5;
    }

}
