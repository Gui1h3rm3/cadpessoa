package br.com.guilherme.cadpessoa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.never;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import br.com.guilherme.cadpessoa.dto.PessoaOutputDTO;
import br.com.guilherme.cadpessoa.enums.TipoUsuarioEnum;
import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.model.CadPessoaUser;
import br.com.guilherme.cadpessoa.model.Pessoa;
import br.com.guilherme.cadpessoa.repositories.CadPessoaUserRepository;
import br.com.guilherme.cadpessoa.repositories.PessoaRepository;
import br.com.guilherme.cadpessoa.services.CadPessoaUserService;
import br.com.guilherme.cadpessoa.services.PessoaService;

@SpringBootTest
@ActiveProfiles("test")
public class PessoaServiceTest {
	
	@SpyBean
	private PessoaService pessoaService;
	
	@MockBean
	private PessoaRepository pessoaRepository;
	
	/*
	@Test
	public void filtraListaDePessoaPorNome() {
		// cenário
		List<Pessoa> listPessoa = new ArrayList<>();
		listPessoa.add(new Pessoa(1L, "Guilherme", "123456", LocalDate.now(), "teste@teste.com"));
		listPessoa.add(new Pessoa(2L, "Galo", "123456", LocalDate.now(), "teste@teste.com"));
		
		Page<Pessoa> pagePessoa = new PageImpl<>(listPessoa);
		Pageable pageable = Pageable.unpaged();
		Mockito.when(this.pessoaRepository.filterByName(Mockito.eq(Mockito.anyString()), pageable)).thenReturn(pagePessoa);
		
		// ação
		Page<Pessoa> retorno = this.pessoaService.filterByName("G", pageable);
		
		// verificação
		assertThat(retorno.getContent()).isNotNull();
		assertThat(retorno.getContent()).hasSize(2);
		assertThat(retorno.getContent().get(0).getNome()).isEqualTo("Guilherme");
		assertThat(retorno.getContent().get(1).getNome()).isEqualTo("Galo");
	}
	*/
	/*
	public void filtraListaDePessoaPorDataNascimento() {
		
	}
	*/
	
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
	
	/*
	@Test
	public void atualizaUmaPessoa() throws CadPessoaUserException {
		// cenário
		Pessoa p = new Pessoa(1L, "Guilherme", "123456", LocalDate.now(), "teste@teste.com");
		Mockito.when(this.pessoaRepository.create(p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail())).thenReturn(p);
		
		Pessoa p2 = new Pessoa(1L, "Teste", "654321", LocalDate.now(), "teste2@teste.com");
		Mockito.when(this.pessoaRepository.update(p.getId(), p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail())).thenReturn(p2);
		//Mockito.when(this.pessoaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p2));
		
		// ação e verificação
		Long id = this.pessoaService.save(null, p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail());
		assertThat(id).isNotNull();
		
		this.pessoaService.save(id, p2.getNome(), p2.getTelefone(), p2.getNascimento(), p2.getEmail());
		
		Optional<Pessoa> p3 = this.pessoaService.getById(id);
		assertThat(p3.get()).isNotNull();
		assertThat(p3.get().getNome()).equals("Teste");
		assertThat(p3.get().getEmail()).equals("teste2@teste.com");
	}
	*/
	
	@Test
	public void removeUmaPessoa() throws CadPessoaUserException {
		// cenário
		Pessoa p = new Pessoa(1L, "Guilherme", "123456", LocalDate.now(), "teste@teste.com");
		Mockito.when(this.pessoaRepository.create(p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail())).thenReturn(p);
		
		// ação e verificação
		Long id = this.pessoaService.save(null, p.getNome(), p.getTelefone(), p.getNascimento(), p.getEmail());
		assertThat(id).isNotNull();
		
		this.pessoaService.remover(id);
		//Optional<Pessoa> p2 = this.pessoaService.getById(id);
		PessoaOutputDTO p2 = this.pessoaService.getById(id);
		//assertThat(p2).isNotPresent();
		assertThat(p2).isNull();
	}
}
