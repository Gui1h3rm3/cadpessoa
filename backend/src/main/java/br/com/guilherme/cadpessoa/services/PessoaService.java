package br.com.guilherme.cadpessoa.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.guilherme.cadpessoa.dto.PessoaOutputDTO;
import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.model.Pessoa;
import br.com.guilherme.cadpessoa.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Page<Pessoa> listarTodos(Pageable pageable) {
		return this.pessoaRepository.findAll(pageable);
	}
	
	public Page<Pessoa> filterByName(String nome, Pageable pageable) {
		return this.pessoaRepository.filterByName(nome, pageable);
	}
	
	public Page<Pessoa> filterByNascimento(LocalDate data, Pageable pageable) {
		return this.pessoaRepository.filterByNascimento(data, pageable);
	}
	
	public Page<Pessoa> filterByMonthAndYear(Long mes, Long ano, Pageable pageable) {
		return this.pessoaRepository.filterByMonthAndYear(mes, ano, pageable);
	}
	
	public PessoaOutputDTO getById(Long id) throws CadPessoaUserException {

		Optional<Pessoa> optPessoa = this.pessoaRepository.findById(id);
		
		if(optPessoa.isEmpty()) {
			throw new CadPessoaUserException("Pessoa não encontrada!");
		}
		
		return optPessoa.get().toDados();
	}
	
	public Long save(Long id, String nome, String telefone, LocalDate nascimento, String email) throws CadPessoaUserException {

		Pessoa pessoa;
		
		if(id == null) {
			pessoa = this.pessoaRepository.create(nome, telefone, nascimento, email);
		} else {
			pessoa = this.pessoaRepository.update(id, nome, telefone, nascimento, email);
		}
		
		return pessoa.getId();

	}
	
	public Boolean remover(Long id) {
		this.pessoaRepository.deleteById(id);
		return Boolean.TRUE;
	}
}