package digital.sepiente.tests.encrypttest.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import digital.sepiente.tests.encrypttest.exception.DemoException;
import digital.sepiente.tests.encrypttest.service.IEncriptService;

@Service("encriptServiceAes")
public class EncriptServiceAes implements IEncriptService {

	private static final String ALGORITHM = "AES";

	@Value("${encode.key}")
	private String keyEncode;

	@Override
	public String encript(String str) throws DemoException {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(keyEncode.getBytes(StandardCharsets.UTF_8.name()), ALGORITHM);
			Cipher cipher;
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] encrypted = cipher.doFinal(str.getBytes());
			return Base64.encodeBase64String(encrypted);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			throw new DemoException(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public String decript(String encrypted) throws DemoException {
		try {
			// AES Encryption
			SecretKeySpec secretKey = new SecretKeySpec(keyEncode.getBytes(StandardCharsets.UTF_8.name()), ALGORITHM);
			// Desencriptamos
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
			// Parseamos a String
			return new String(original, StandardCharsets.UTF_8.name());
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			throw new DemoException(e.getLocalizedMessage(), e);
		}
	}
}
