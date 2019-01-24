package com.example.mongo.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mongo.azure.KeyVaultConfiguration;
import com.example.mongo.azure.KeyVaultCredential;
import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.models.SecretBundle;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;




@Configuration
public class IdentityMongoConfiguration {

	@Autowired
	KeyVaultConfiguration keyVaultConfiguration;

	@Value("${identity.mongodb.keyvault.secretname}")
	String mongoSecretName;

	@Bean
	public MongoClient mongoClient(String dbCollection) throws UnknownHostException {

		KeyVaultClient keyVaultClient = new KeyVaultClient(new KeyVaultCredential(
				keyVaultConfiguration.getKeyVaultClientId(), keyVaultConfiguration.getKeyVaultClientKey()+"="));
		System.out.println("keyVaultConfiguration.getKeyVaultClientId() "+ keyVaultConfiguration.getKeyVaultClientId());
		System.out.println("keyVaultConfiguration.getKeyVaultClientKey() "+ keyVaultConfiguration.getKeyVaultClientKey());
		SecretBundle secretBundle = keyVaultClient.getSecret(keyVaultConfiguration.getKeyVaultURI(), mongoSecretName);
		System.out.println("secretBundle :  "+ secretBundle.value());
		MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();
		optionsBuilder.socketTimeout(10000);
		optionsBuilder.maxConnectionIdleTime(60000);
		optionsBuilder.heartbeatConnectTimeout(5000);
		MongoClientURI mongoClientURI = new MongoClientURI(secretBundle.value(), optionsBuilder);
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		mongoClient.getDatabase(dbCollection);

		return mongoClient;
		}
}
