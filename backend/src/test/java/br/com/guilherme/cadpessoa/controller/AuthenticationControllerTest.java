package br.com.guilherme.cadpessoa.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.guilherme.cadpessoa.dto.RegisterDTO;
import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.services.CadPessoaUserService;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
	
	private final String API = "/auth";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CadPessoaUserService cadUserPessoaService;
	
	@Test
	@WithMockUser(username = "guilherme", password = "123456")
	public void deveRegistrarUmUsuario() throws Exception {
		// cenário
		RegisterDTO registerDTO = new RegisterDTO("teste", "123", "ADMIN");
		Mockito.doNothing()
			.when(this.cadUserPessoaService)
			.register(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getTipoUsuario());
		String json = new ObjectMapper().writeValueAsString(registerDTO);
		
		// ação
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(this.API.concat("/register"))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		
		// verificação
		this.mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deveRetornarErroAoRegistrarUsuario() throws Exception {
		// cenário
		RegisterDTO registerDTO = new RegisterDTO("teste", "123", "ADMIN");
		Mockito.doThrow(CadPessoaUserException.class)
			.when(this.cadUserPessoaService)
			.register(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getTipoUsuario());
		String json = new ObjectMapper().writeValueAsString(registerDTO);
		
		// ação
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(this.API.concat("/register"))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		
		// verificação
		this.mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
