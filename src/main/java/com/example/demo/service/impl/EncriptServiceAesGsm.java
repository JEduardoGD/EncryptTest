package com.example.demo.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DemoException;
import com.example.demo.service.IEncriptService;

@Service("encriptServiceAesGsm")
public class EncriptServiceAesGsm implements IEncriptService {

	private static final String ALGORITHM = "AES/GCM/NoPadding";

	@Value("${encode.key}")
	private String keyEncode;
	
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;
	
	@Override
	public String encript(String str) throws DemoException {
		SecretKey key = new SecretKeySpec(keyEncode.getBytes(), "AES");
		try {
			 byte[] iv = new byte[GCM_IV_LENGTH];
		        (new SecureRandom()).nextBytes(iv);

		        Cipher cipher = Cipher.getInstance(ALGORITHM);
		        GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
		        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

		        byte[] ciphertext = cipher.doFinal(str.getBytes("UTF8"));
		        byte[] encrypted = new byte[iv.length + ciphertext.length];
		        System.arraycopy(iv, 0, encrypted, 0, iv.length);
		        System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

		        String encoded = Base64.getEncoder().encodeToString(encrypted);

		        return encoded;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException | InvalidAlgorithmParameterException e) {
			throw new DemoException(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public String decript(String encrypted) throws DemoException {
		SecretKey key = new SecretKeySpec(keyEncode.getBytes(), "AES");
		try {
			byte[] decoded = Base64.getDecoder().decode(encrypted);

	        byte[] iv = Arrays.copyOfRange(decoded, 0, GCM_IV_LENGTH);

	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
	        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

	        byte[] ciphertext = cipher.doFinal(decoded, GCM_IV_LENGTH, decoded.length - GCM_IV_LENGTH);

	        String result = new String(ciphertext, "UTF8");

	        return result;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException | InvalidAlgorithmParameterException e) {
			throw new DemoException(e.getLocalizedMessage(), e);
		}
	}
}
