package br.com.guilherme.cadpessoa.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private String token;

	public TokenDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}