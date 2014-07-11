package com.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtil {

	private static Key key;
	
	public static String KEY_STR = "key";
	
	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
		} catch (NoSuchAlgorithmException e) {
			
		}
	}
	
	public static String getEncryptString(String str){
		BASE64Encoder base64en = new BASE64Encoder();
		Cipher cipher;
		byte[] encryptStrBytes = null;
		try {
			cipher = Cipher.getInstance("UTF8");
			byte[] strBytes = str.getBytes("UTF8");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptStrBytes = cipher.doFinal(strBytes);
		} catch (Exception e) {
			
		}
		return base64en.encode(encryptStrBytes);
	}
	
	public static String getDecryptString(String str){
		BASE64Decoder base64De = new BASE64Decoder();
		Cipher cipher;
		byte[] decryptStrBytes;
		String destr = "";
		try {
			byte[] strBytes = base64De.decodeBuffer(str);
			cipher = Cipher.getInstance("UTF8");
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryptStrBytes = cipher.doFinal(strBytes);
			destr= new String(decryptStrBytes, "UTF8");
		} catch (Exception e){
			
		}
		return destr;
	}
	
	
}

