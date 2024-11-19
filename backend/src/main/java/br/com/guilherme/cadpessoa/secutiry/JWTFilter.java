package br.com.guilherme.cadpessoa.secutiry;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.guilherme.cadpessoa.services.CadPessoaUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	private final JWTUtil jwtUtil;
	private final CadPessoaUserService cadPessoaUserService;
	
	public JWTFilter(JWTUtil jwtUtil, CadPessoaUserService cadPessoaUserService) {
		super();
		this.jwtUtil = jwtUtil;
		this.cadPessoaUserService = cadPessoaUserService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws ServletException, IOException {
		
		String authorizationHeader = this.jwtUtil.getAutorizationHeader();
		String requestHeader = request.getHeader(authorizationHeader);
		
		if(requestHeader != null) {
			
			String token = requestHeader.replace(this.jwtUtil.getTokenPrefix(), "");
			String username = this.jwtUtil.validateToken(token);
			
			UserDetails user = this.cadPessoaUserService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());			
			SecurityContextHolder.getContext().setAuthentication(authentication);			
		}
		
		filterChain.doFilter(request, response);
	}
}