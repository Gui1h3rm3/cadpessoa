package br.com.guilherme.cadpessoa.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PessoaOutputDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;	
	private String telefone;	
	private LocalDate nascimento;
	private String email;

	public PessoaOutputDTO(Long id, String nome, String telefone, LocalDate nascimento, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
