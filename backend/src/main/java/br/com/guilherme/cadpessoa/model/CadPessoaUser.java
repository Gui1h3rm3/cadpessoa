package br.com.guilherme.cadpessoa.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.guilherme.cadpessoa.enums.TipoUsuarioEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cad_pessoa_user")
public class CadPessoaUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_CADPESSOAUSER", sequenceName = "SEQ_CADPESSOAUSER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CADPESSOAUSER")
	@Column(name = "id", precision = 38, scale = 0, updatable = false, insertable = false)
	private Long id;
	
	@Column(name = "username", length = 50, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "tipo_usuario", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuarioEnum tipoUsuario;
	
	public CadPessoaUser() {
		super();
	}

	public CadPessoaUser(String username, String password, TipoUsuarioEnum tipoUsuario) {
		super();		
		this.username = username;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.tipoUsuario == TipoUsuarioEnum.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}		
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoUsuarioEnum getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}