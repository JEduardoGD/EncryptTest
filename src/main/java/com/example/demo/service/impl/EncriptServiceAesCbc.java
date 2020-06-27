package com.example.demo.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DemoException;
import com.example.demo.service.IEncriptService;

@Service("encriptServiceAesCbc")
public class EncriptServiceAesCbc implements IEncriptService {

	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	
	private final static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
	private static final IvParameterSpec ivspec = new IvParameterSpec(iv);

	@Value("${encode.key}")
	private String keyEncode;
	
	@PostConstruct void init(){
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	@Override
	public String encript(String str) throws DemoException {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(keyEncode.getBytes(StandardCharsets.UTF_8.name()), "AES");
			Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

			byte[] encrypted = cipher.doFinal(str.getBytes());
			return Base64.encodeBase64String(encrypted);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			throw new DemoException(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public String decript(String encrypted) throws DemoException {
		try {
			// AES Encryption
			SecretKeySpec secretKey = new SecretKeySpec(keyEncode.getBytes(StandardCharsets.UTF_8.name()), "AES");
			// Desencriptamos
			Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
			// Parseamos a String
			return new String(original, StandardCharsets.UTF_8.name());
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			throw new DemoException(e.getLocalizedMessage(), e);
		}
	}
}
