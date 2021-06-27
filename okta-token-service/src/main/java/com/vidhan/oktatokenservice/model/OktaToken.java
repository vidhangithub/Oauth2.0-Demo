package com.vidhan.oktatokenservice.model;

public class OktaToken {
	
	public OktaToken() {
	
	}
	private String accessToken;
	private int expiresInSec;
	private String idToken;
	private String scope;
	private String tokenType;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresInSec() {
		return expiresInSec;
	}
	public void setExpiresInSec(int expiresInSec) {
		this.expiresInSec = expiresInSec;
	}
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	
	
}
