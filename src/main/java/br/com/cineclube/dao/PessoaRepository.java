package br.com.cineclube.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cineclube.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	// retorna pessoas com dataNasc anterior a data
	List<Pessoa> findByDataNascBefore(LocalDate data);
	
	// retorna pessoas com dataNasc maior que data
	List<Pessoa> findByDataNascAfter(LocalDate data);
}
