package br.com.guilherme.cadpessoa.filter;

import java.io.Serializable;
import java.time.LocalDate;

public class FilterPessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private LocalDate dataNascimento;
	
	public FilterPessoaDTO() {
		
	}

	public FilterPessoaDTO(String nome, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
