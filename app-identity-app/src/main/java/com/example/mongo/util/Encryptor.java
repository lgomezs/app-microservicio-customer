package com.example.mongo.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Encryptor {
	private static final String UTF_8 = "UTF-8";
	private static final String AES = "AES";
	private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";

	private static final Logger logger = LoggerFactory.getLogger(Encryptor.class);

	/*protected Encryptor() {
		throw new IllegalAccessError("Clase de Encriptado con metodos estaticos");
	}*/

	public static Cipher initChipher(String key, String initVector, int mode) {

		Cipher cipher = null;
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF_8), AES);
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(mode, skeySpec, iv);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return cipher;
	}

	public static String encrypt(Cipher cipher, String value) {
		try {

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			logger.error(ex.toString(), ex);
		}

		return null;
	}

	public static String decrypt(Cipher cipher, String encrypted) {
		try {

			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
			return new String(original);
		} catch (Exception ex) {
			logger.error(ex.toString(), ex);
		}

		return null;
	}
}
