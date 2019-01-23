package com.example.mongo.azure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class KeyVaultConfiguration {

	@Value("${identity.keyvault.uri}")
	String keyVaultURI;

	@Value("${identity.keyvault.clientid}")
	String keyVaultClientId;

	@Value("${identity.keyvault.clientkey}")
	String keyVaultClientKey;

}
