package br.com.guilherme.cadpessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilherme.cadpessoa.dto.EntityIdDTO;
import br.com.guilherme.cadpessoa.dto.PessoaInputDTO;
import br.com.guilherme.cadpessoa.dto.PessoaOutputDTO;
import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.filter.FilterPessoaDTO;
import br.com.guilherme.cadpessoa.services.PessoaService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/listAll")
	public Page<?> listAll(Pageable pageable) {
		return this.pessoaService.listarTodos(pageable);
	}
	
	@GetMapping("/filterByName")
	public Page<?> filterByName(@RequestParam("nome") String nome, Pageable pageable) {
		return this.pessoaService.filterByName(nome, pageable);
	}
	
	@GetMapping("/filterByDataNascimento")
	public Page<?> filterByDataNascimento(@RequestBody FilterPessoaDTO filter, Pageable pageable) {
		return this.pessoaService.filterByNascimento(filter.getDataNascimento(), 
														pageable);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") @Nonnull Long id) throws CadPessoaUserException {
		PessoaOutputDTO pessoaOutputDto = this.pessoaService.getById(id);
		return ResponseEntity.ok(pessoaOutputDto);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody @Valid PessoaInputDTO dto) throws CadPessoaUserException {
		Long id = this.pessoaService.save(dto.getId(), 
											dto.getNome(), 
											dto.getTelefone(), 
											dto.getNascimento(), 
											dto.getEmail());
		return ResponseEntity.ok(new EntityIdDTO(id));
	}
	
	@PostMapping("/remove")
	public ResponseEntity<?> remove(@RequestBody @Valid EntityIdDTO dto) throws CadPessoaUserException {
		Boolean removeu = this.pessoaService.remover(dto.getId());
		return ResponseEntity.ok(removeu);
	}
}