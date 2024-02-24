package com.rawbank.admin.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rawbank.admin.config.RawAppConfig;



public class RawAESEncryptionDecryptionService {

	private static SecretKeySpec secretKeySpec;
	private static byte[] KEY;
	private static final String ALGORITHM = "AES";

	private static final Logger LOGGER = LoggerFactory.getLogger(RawAESEncryptionDecryptionService.class);

	private void prepareSecreteKey(String myKey) {
		MessageDigest sha = null;
		try {
			KEY = myKey.getBytes(StandardCharsets.UTF_8);
			sha = MessageDigest.getInstance("SHA-1");
			KEY = sha.digest(KEY);
			KEY = Arrays.copyOf(KEY, 16);
			secretKeySpec = new SecretKeySpec(KEY, ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String strToEncrypt) {
		try {
			prepareSecreteKey(RawAppConfig.getAESsecretKey());
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			LOGGER.error("Error while encrypting:  {}", e.toString());
		}
		return null;
	}

	public String decrypt(String strToDecrypt) {
		try {
			prepareSecreteKey(RawAppConfig.getAESsecretKey());
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			LOGGER.error("Error while encrypting:  {}", e.toString());
		}
		return null;
	}
//


}
