package br.com.cineclube.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cineclube.model.Filme;

// T - type
// ID - tipo do filmeId
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
}
