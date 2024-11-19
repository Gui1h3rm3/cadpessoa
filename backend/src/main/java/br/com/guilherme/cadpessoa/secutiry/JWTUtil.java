package br.com.guilherme.cadpessoa.secutiry;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.guilherme.cadpessoa.model.CadPessoaUser;

@Service
public class JWTUtil {
	
	@Value("${api.security.token.secret}")
	private String tokenSecret;
	private final String autorizationHeader = "Authorization";
	private final String tokenPrefix = "Bearer ";
	private GregorianCalendar tokenExpiration = new GregorianCalendar();
	
	public String generateToken(CadPessoaUser cadPessoaUser) {
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(this.tokenSecret);
			
			String token = JWT.create()
					.withIssuer("cad-pessoa")
					.withSubject(cadPessoaUser.getUsername())
					.withClaim("authorites", cadPessoaUser.getAuthorities()
							.stream()
							.map(a -> a.getAuthority()).collect(Collectors.toList()))
					.withExpiresAt(this.getExpirationDate())
					.sign(algorithm);
			
			return this.tokenPrefix + token;
			
		} catch (JWTVerificationException e) {
			throw new RuntimeException("Erro ao gerar token", e);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(this.tokenSecret);
			return JWT.require(algorithm)
					.withIssuer("cad-pessoa")
					.build()
					.verify(token)
					.getSubject();			
		} catch (JWTVerificationException e) {
			return "";
		}
	}
	
	private Instant getExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public String getAutorizationHeader() {
		return autorizationHeader;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public GregorianCalendar getTokenExpiration() {
		return tokenExpiration;
	}
}
