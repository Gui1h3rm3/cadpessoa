package br.com.guilherme.cadpessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilherme.cadpessoa.dto.LoginDTO;
import br.com.guilherme.cadpessoa.dto.RegisterDTO;
import br.com.guilherme.cadpessoa.dto.TokenDTO;
import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.model.CadPessoaUser;
import br.com.guilherme.cadpessoa.secutiry.JWTUtil;
import br.com.guilherme.cadpessoa.services.CadPessoaUserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private CadPessoaUserService cadPessoaUserService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
		Authentication authenticate = this.authenticationManager.authenticate(authentication);
		String token = this.jwtUtil.generateToken((CadPessoaUser) authenticate.getPrincipal());
		
		return ResponseEntity.ok(new TokenDTO(token));
	}
	
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) throws CadPessoaUserException {
    	this.cadPessoaUserService.register(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getTipoUsuario());
    	return ResponseEntity.ok().build();
    }
}