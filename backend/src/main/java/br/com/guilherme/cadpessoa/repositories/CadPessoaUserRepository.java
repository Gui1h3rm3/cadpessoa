package br.com.guilherme.cadpessoa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guilherme.cadpessoa.enums.TipoUsuarioEnum;
import br.com.guilherme.cadpessoa.model.CadPessoaUser;

public interface CadPessoaUserRepository extends JpaRepository<CadPessoaUser, Long> {
	
	public Optional<CadPessoaUser> findByUsername(String username);
	
	default CadPessoaUser create(String username, String password, String tipoUsuario) {
		CadPessoaUser user = new CadPessoaUser(username, password, TipoUsuarioEnum.getEnum(tipoUsuario));
		return save(user);
	}
}