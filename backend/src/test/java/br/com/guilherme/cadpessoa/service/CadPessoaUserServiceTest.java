package br.com.guilherme.cadpessoa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.never;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.guilherme.cadpessoa.enums.TipoUsuarioEnum;
import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.model.CadPessoaUser;
import br.com.guilherme.cadpessoa.repositories.CadPessoaUserRepository;
import br.com.guilherme.cadpessoa.services.CadPessoaUserService;

@SpringBootTest
@ActiveProfiles("test")
public class CadPessoaUserServiceTest {

	@SpyBean
	private CadPessoaUserService cadPessoaUserService;
	
	@MockBean
	private CadPessoaUserRepository cadPessoaUserRepository;
	
	@Test
	public void deveBuscarUmUsuarioPeloUsername() {
		
		// cenário
		CadPessoaUser usuario = new CadPessoaUser("user", "123456", TipoUsuarioEnum.ADMIN);
		
		Mockito.when(this.cadPessoaUserRepository
				.findByUsername(Mockito.anyString()))
				.thenReturn(Optional.of(usuario));
		
		// ação
		Optional<CadPessoaUser> optCadPessoaUser = this.cadPessoaUserRepository.findByUsername("user");
		
		// verificação
		assertThat(optCadPessoaUser.get()).isNotNull();
		assertThat(optCadPessoaUser.get().getUsername()).isEqualTo("user");
		assertThat(optCadPessoaUser.get().getPassword()).isEqualTo("123456");
		assertThat(optCadPessoaUser.get().getTipoUsuario()).isEqualTo(TipoUsuarioEnum.ADMIN);
	}
	
	@Test
	public void deveCadastrarUmUsuario() {
		
		// cenário		
		Mockito.when(this.cadPessoaUserRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
		
		catchThrowableOfType(() -> this.cadPessoaUserService.register("user", "123456", TipoUsuarioEnum.ADMIN.toString()), CadPessoaUserException.class);		
		catchThrowableOfType(() -> Mockito.verify(this.cadPessoaUserService).register("user", "123456", TipoUsuarioEnum.ADMIN.toString()), 
				CadPessoaUserException.class);
	}
	
	@Test
	public void deveRetornarErroAoCadastrarUmUsuarioQueJaExiste() {
		
		// cenário
		CadPessoaUser usuario = new CadPessoaUser("user", "123456", TipoUsuarioEnum.ADMIN);		
		Mockito.when(this.cadPessoaUserRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		// ação
		catchThrowableOfType(() -> this.cadPessoaUserService.register("user", "123456", TipoUsuarioEnum.ADMIN.toString()), CadPessoaUserException.class);
		
		// verificação
		Mockito.verify(this.cadPessoaUserRepository, never()).create("user", "123456", TipoUsuarioEnum.ADMIN.toString());
	}
	
	@Test
	public void deveRetornarErroAoCadastrarUmUsuarioComTipoDeUsuarioNulo() {
		
		// cenário
		Mockito.when(this.cadPessoaUserRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
		
		// ação
		catchThrowableOfType(() -> this.cadPessoaUserService.register("user", "123456", null), CadPessoaUserException.class);
		
		// verificação
		Mockito.verify(this.cadPessoaUserRepository, never()).create("user", "123456", TipoUsuarioEnum.ADMIN.toString());
	}
}
