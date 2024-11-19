package br.com.guilherme.cadpessoa.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

public class EntityIdDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "id é obrigatório.")
	private Long id;

	public EntityIdDTO() {

	}

	public EntityIdDTO(Long id) {	
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
