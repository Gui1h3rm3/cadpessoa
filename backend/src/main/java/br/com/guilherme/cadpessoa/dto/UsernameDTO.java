package br.com.guilherme.cadpessoa.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class UsernameDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Nome de usuário é obrigatório")
	private String username;

	public UsernameDTO(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}