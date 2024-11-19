package br.com.guilherme.cadpessoa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.model.Pessoa;
import br.com.guilherme.cadpessoa.repositories.PessoaRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PessoaRepositoryTest {
	
	@Autowired
	private PessoaRepository pessoaRepository;
		
	@Test
	public void deveSalvarUmaPessoa() throws CadPessoaUserException {
		Pessoa pessoa = this.pessoaRepository.create("guilherme", "031123456789", LocalDate.now(), "teste@email.com");
		assertThat(pessoa.getId()).isNotNull();
		assertThat(pessoa.getNome()).isEqualTo("guilherme");
		assertThat(pessoa.getTelefone()).isEqualTo("031123456789");
		assertThat(pessoa.getNascimento()).isEqualTo(LocalDate.now());
		assertThat(pessoa.getEmail()).isEqualTo("teste@email.com");
	}
	
	@Test
	public void deveAtualizarUmaPessoa() throws CadPessoaUserException {
		
		Pessoa pessoa = this.pessoaRepository.create("guilherme", "031123456789", LocalDate.now(), "teste@email.com");
		
		Pessoa pessoaAtualizada = this.pessoaRepository.update(pessoa.getId(), "teste", "999999999999", LocalDate.now(), "testeAtualizado@email.com");
		
		assertThat(pessoaAtualizada.getId()).isNotNull();
		assertThat(pessoaAtualizada.getNome()).isEqualTo("teste");
		assertThat(pessoaAtualizada.getTelefone()).isEqualTo("999999999999");
		assertThat(pessoaAtualizada.getNascimento()).isEqualTo(LocalDate.now());
		assertThat(pessoaAtualizada.getEmail()).isEqualTo("testeAtualizado@email.com");
	}
}