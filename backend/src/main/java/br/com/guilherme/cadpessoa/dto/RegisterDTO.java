package br.com.guilherme.cadpessoa.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class RegisterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Nome de usuário é obrigatório")
	private String username;
	
	@NotBlank(message = "Senha é obrigatória")
	private String password;
	
	@NotBlank(message = "Tipo de usuário é obrigatório")
	private String tipoUsuario;
	
	public RegisterDTO() {
		super();
	}
	
	public RegisterDTO(String username, String password, String tipoUsuario) {
		super();
		this.username = username;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
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

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
