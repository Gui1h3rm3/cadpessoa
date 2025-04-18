package br.com.guilherme.cadpessoa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.guilherme.cadpessoa.dto.PessoaOutputDTO;
import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.model.Pessoa;
import br.com.guilherme.cadpessoa.repositories.PessoaRepository;
import br.com.guilherme.cadpessoa.services.PessoaService;

@SpringBootTest
@ActiveProfiles("test")
public class PessoaServiceTest {
	
	@SpyBean
	private PessoaService pessoaService;
	
	@MockBean
	private PessoaRepository pessoaRepository;
	
	
	@Test
	public void salvaUmaNovaPessoa() throws CadPessoaUserException {
		// cenário
		Pessoa p = new Pessoa(1L, "Guilherme", "123456", LocalDate.now(), "teste@teste.com");
		Mockito.when(this.pessoaRepository.create(p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail())).thenReturn(p);
		
		// ação
		Long id = this.pessoaService.save(null, p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail());
		
		// verificação
		assertThat(id).isNotNull();
	}	
	
	@Test
	public void atualizaUmaPessoa() throws CadPessoaUserException {
		
		// cenário
		Pessoa p = new Pessoa(1L, "Guilherme", "123456", LocalDate.now(), "teste@teste.com");
		Pessoa p2 = new Pessoa(1L, "Teste", "654321", LocalDate.now(), "teste2@teste.com");
		
		Mockito.when(this.pessoaRepository.create(p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail())).thenReturn(p);
		Mockito.when(this.pessoaRepository.update(p.getId(), p2.getNome(), p2.getTelefone(), p2.getNascimento(), p2.getEmail())).thenReturn(p2);
		Mockito.when(this.pessoaRepository.findById(1L)).thenReturn(Optional.of(p2));
		
		// ação e verificação
		Long id = this.pessoaService.save(null, p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail());
		
		assertThat(id).isNotNull();
		
		this.pessoaService.save(id, p2.getNome(), p2.getTelefone(), p2.getNascimento(), p2.getEmail());
		
		PessoaOutputDTO pessoaDTO = this.pessoaService.getById(id);
		assertThat(pessoaDTO.getId()).isNotNull();
		assertThat(pessoaDTO.getNome()).isEqualTo("Teste");
		assertThat(pessoaDTO.getEmail()).isEqualTo("teste2@teste.com");
	}
	
	@Test
	public void removeUmaPessoa() throws CadPessoaUserException {
		// cenário
		Pessoa p = new Pessoa(1L, "Guilherme", "123456", LocalDate.now(), "teste@teste.com");
		Mockito.when(this.pessoaRepository.create(p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail())).thenReturn(p);
		
		// ação e verificação
		Long id = this.pessoaService.save(null, p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail());
		assertThat(id).isNotNull();
		
		this.pessoaService.remover(id);
		assertThrows(CadPessoaUserException.class, () -> this.pessoaService.getById(id));
	}
}
