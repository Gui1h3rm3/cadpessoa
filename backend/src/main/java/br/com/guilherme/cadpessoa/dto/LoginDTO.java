package br.com.guilherme.cadpessoa.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Nome de usuário é obrigatório")
	private String username;
	
	@NotNull(message = "Senha é obrigatória")
	private String password;	
	
	public LoginDTO() {
		super();
	}

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
