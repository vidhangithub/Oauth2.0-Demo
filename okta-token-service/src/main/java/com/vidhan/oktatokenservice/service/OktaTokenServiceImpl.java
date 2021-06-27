package com.vidhan.oktatokenservice.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidhan.oktatokenservice.model.OktaToken;

@Service
public class OktaTokenServiceImpl implements OktaTokenService{
	
	@Override
	public OktaToken getToken(String authToken) {
		try  {
			HttpClient httpClient = HttpClient.newHttpClient();
			URI uri= new URI(OktaTokenService.baseUrl+"/v1/token/" +"?client_id="+"0oacjqr4gat2WZKGW5d6"+"&"
					+"client_secret="+"p7KoA6zQmjNp9YW5KSKFZfuO4fqpFpTFccCiIzjH"+"&"
					+"grant_type="+"authorization_code"+"&"
					+"redirect_uri="+"http://localhost:8181/api/v1/helloToken"+"&"
					+"code="+authToken);
			HttpRequest request = HttpRequest.newBuilder(
					uri)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(HttpRequest.BodyPublishers.ofString(uri.toString()))
					.build();
			HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(httpResponse.body());
			JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.body());
			if (jsonNode !=null && !jsonNode.isEmpty() && !jsonNode.hasNonNull("error") ) {
				OktaToken token = new OktaToken();
				token.setTokenType(jsonNode.get("token_type").toString());
				token.setExpiresInSec(Integer.parseInt(jsonNode.get("expires_in").toString()) );
				token.setAccessToken(jsonNode.get("access_token").toString());
				token.setScope(jsonNode.get("scope").toString());
				token.setIdToken(jsonNode.get("id_token").toString());
				return token;
			}	
		
		} catch (URISyntaxException e) {
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
		return null;
	}
	
}
