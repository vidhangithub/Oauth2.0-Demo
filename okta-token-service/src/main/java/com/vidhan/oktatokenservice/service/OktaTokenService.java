package com.vidhan.oktatokenservice.service;

import com.vidhan.oktatokenservice.model.OktaToken;

public interface OktaTokenService {

	String baseUrl = "https://dev-42317725.okta.com/oauth2/default";
	OktaToken getToken(String authToken);
	
	
}
