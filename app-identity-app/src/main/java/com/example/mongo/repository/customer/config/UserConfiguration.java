package com.example.mongo.repository.customer.config;


import javax.crypto.Cipher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.mongo.util.Encryptor;

import lombok.Getter;


@Getter
@Configuration
public class UserConfiguration {
	
	@Value("${encrypt.key}")
	String encryptKey;

	@Primary
	@Bean(name = "cipherEncrypt")
	public Cipher cipherEncrypt() {
		return Encryptor.initChipher(encryptKey,encryptKey,Cipher.ENCRYPT_MODE);
	}
	
	@Bean(name = "cipherDecrypt")
	public Cipher cipherDecrypt() {
		return Encryptor.initChipher(encryptKey,encryptKey,Cipher.DECRYPT_MODE);
	}
	
}
