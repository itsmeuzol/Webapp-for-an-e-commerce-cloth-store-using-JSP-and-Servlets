package com.lugapasal.resources;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	 private static final String ALGORITHM = "AES";
	 private static final String KEY = "mysecretkey12345";

	/**
	* Encrypts a string using AES. This is used to encrypt data that is stored in the database.
	* 
	* @param value - The value to encrypt. Must be non - null.
	* 
	* @return The encrypted value. Must be non - null and base64 encoded as a string ( no spaces in between
	*/
	public static String encrypt(String value) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedValue = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
    * Decrypt a Base64 encoded string. This is used to decrypt data that was encrypted with #encrypt ( String )
    * 
    * @param value - The string to decrypt.
    * 
    * @return The decrypted string or null if there was an error decrypting the string ( for example if the string was not encrypted
    */
    public static String decrypt(String value) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedValue = Base64.getDecoder().decode(value.getBytes());
            byte[] decryptedValue = cipher.doFinal(decodedValue);
            return new String(decryptedValue);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

