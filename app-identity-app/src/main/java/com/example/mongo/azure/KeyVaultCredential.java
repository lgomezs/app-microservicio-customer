package com.example.mongo.azure;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.keyvault.authentication.KeyVaultCredentials;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyVaultCredential extends KeyVaultCredentials {
	private String clientId;
	private String clientKey;

	public KeyVaultCredential(String clientId, String clientKey) {
		this.clientId = clientId;
		this.clientKey = clientKey;
	}

	@Override
	public String doAuthenticate(String authorization, String resource, String scope) {
		Objects.requireNonNull(clientKey, "Application secret is a required value");
		AuthenticationResult token = getAccessTokenFromClientCredentials(authorization, resource, clientId, clientKey);
		return token.getAccessToken();
	}

	private static AuthenticationResult getAccessTokenFromClientCredentials(String authorization, String resource,
			String clientId, String clientKey) {
		AuthenticationContext context = null;
		AuthenticationResult result = null;
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(1);
			context = new AuthenticationContext(authorization, false, service);
			ClientCredential credentials = new ClientCredential(clientId, clientKey);
			Future<AuthenticationResult> future = context.acquireToken(resource, credentials, null);
			result = future.get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}

		if (result == null) {
			throw new RuntimeException("Authentication result was null");
		}
		return result;
	}
}