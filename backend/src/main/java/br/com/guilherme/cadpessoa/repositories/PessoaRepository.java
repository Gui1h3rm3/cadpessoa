package br.com.guilherme.cadpessoa.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.guilherme.cadpessoa.exception.CadPessoaUserException;
import br.com.guilherme.cadpessoa.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query("SELECT p FROM Pessoa p WHERE lower(p.nome) LIKE lower(concat('%', :nome, '%')) ORDER BY p.nome ASC")
	public Page<Pessoa> filterByName(String nome, Pageable pageable);
	
	@Query("SELECT p FROM Pessoa p WHERE p.nascimento = :data ORDER BY p.nome ASC")
	public Page<Pessoa> filterByNascimento(LocalDate data, Pageable pageable);
	
	default Pessoa create(String nome, String telefone, LocalDate nascimento, String email) throws CadPessoaUserException {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setTelefone(telefone);
		pessoa.setNascimento(nascimento);
		pessoa.setEmail(email);
		
		return save(pessoa);
	}
	
	default Pessoa update(Long id, String nome, String telefone, LocalDate nascimento, String email) throws CadPessoaUserException {
		
		Pessoa pessoa = this.findById(id).get();
		pessoa.setNome(nome);
		pessoa.setTelefone(telefone);
		pessoa.setNascimento(nascimento);
		pessoa.setEmail(email);
		
		save(pessoa);
		flush();
		
		return pessoa;
	}
}