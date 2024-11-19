package br.com.guilherme.cadpessoa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.guilherme.cadpessoa.enums.TipoUsuarioEnum;
import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.model.CadPessoaUser;
import br.com.guilherme.cadpessoa.repositories.CadPessoaUserRepository;

@Service
public class CadPessoaUserService implements UserDetailsService {
	
	@Autowired
	private CadPessoaUserRepository cadUserPessoaRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.cadUserPessoaRepository.findByUsername(username).get();
	}
	
	public void register(String username, String password, String tipoUsuario) throws CadPessoaUserException {
		
		Optional<CadPessoaUser> optCadPessoaUser = this.cadUserPessoaRepository.findByUsername(username);
		
		if(optCadPessoaUser.isPresent()) {
			throw new CadPessoaUserException("Usuário já existe!");
		}
		
		if(TipoUsuarioEnum.getEnum(tipoUsuario) == null) {
			throw new CadPessoaUserException("Tipo de usuário inválido!");
		}
    	
    	String encryptedPassword = this.passwordEncoder.encode(password);
    	this.cadUserPessoaRepository.create(username, encryptedPassword, tipoUsuario);
	}
}
