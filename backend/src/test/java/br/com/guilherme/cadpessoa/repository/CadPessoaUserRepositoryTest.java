package br.com.guilherme.cadpessoa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.guilherme.cadpessoa.model.CadPessoaUser;
import br.com.guilherme.cadpessoa.repositories.CadPessoaUserRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CadPessoaUserRepositoryTest {
	
	@Autowired
	private CadPessoaUserRepository cadPessoaUserRepository;
		
	@Test
	public void deveSalvarUmUsuario() {
		CadPessoaUser cadPessoaUser = this.cadPessoaUserRepository.create("guiadmin", "123456", "admin");
		assertThat(cadPessoaUser.getId()).isNotNull();
	}
	
	@Test
	public void deveRetornarUmUsuarioPorUsername() {
		this.cadPessoaUserRepository.create("guiadmin", "123456", "admin");
		Optional<CadPessoaUser> optCadPessoaUser = this.cadPessoaUserRepository.findByUsername("guiadmin");
		assertThat(optCadPessoaUser.isPresent()).isTrue();
	}
}
