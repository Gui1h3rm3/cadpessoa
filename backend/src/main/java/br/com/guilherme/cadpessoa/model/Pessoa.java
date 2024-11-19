package br.com.guilherme.cadpessoa.model;

import java.time.LocalDate;
import java.util.Objects;

import br.com.guilherme.cadpessoa.dto.PessoaOutputDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@SequenceGenerator(name = "SEQ_PESSOA", sequenceName = "SEQ_PESSOA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PESSOA")
	@Column(name = "id", precision = 38, scale = 0, updatable = false, insertable = false)
	private Long id;
	
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@Column(name = "telefone", length = 15)
	private String telefone;
	
	@Column(name = "nascimento", nullable = false)
	private LocalDate nascimento;
	
	@Column(name = "email")
	private String email;

	public Pessoa() {
		super();
	}
	
	public Pessoa(Long id, String nome, String telefone, LocalDate nascimento, String email) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

	public PessoaOutputDTO toDados() {
		return new PessoaOutputDTO(this.id, 
				this.nome, 
				this.telefone, 
				this.nascimento, 
				this.email);
	}
}
