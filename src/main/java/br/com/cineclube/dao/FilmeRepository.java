package br.com.cineclube.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cineclube.model.Filme;

// T - type
// ID - tipo do filmeId
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
	/**
	 * QUERY METHOD
	 * ------------
	 * Com query method nós especificamos o nome do método e o spring gera a query de acordo com uma
	 * convenção de nomeação do método. Portanto nesse caso precisamos escrever o nome do método
     * seguindo essas regras.
	 * Essa metodologia é bastante prática para definir queries mais simples.
	 */
	// Exemplos de "QUERY METHOD"
	
	// derivar a query a partir do nome do metodo (spring data)
	List<Filme> findByCategoria(String categoria);
	// 	
	// SEGUE O PADRÃO: "find" "By" "NomeDoAtributo"
	// Select * from filme where nome = 'Avatar' => essa seria a query gerada pelo Spring
	// com base no nome do metodo findByNome
	List<Filme> findByNome(String nome);
	
	// multiplos atributos no where usar "And"
	// Select * from filme where nome = 'Avatar' and categoria = 'SCIFI'
	List<Filme> findByNomeAndCategoria(String nome, String categoria);
	
	// condicao OR
	List<Filme> findByNomeOrCategoria(String nome, String categoria);
	
	// seleciona todos os filmes e ordena por categoria (padrao eh ascendente)
	List<Filme> findByOrderByCategoria();
	
	// ordenar decrescente - "Desc" no final do nome do metodo
	List<Filme> findByOrderByCategoriaDesc();
	
	// SELECIONAR POR maior, maior igual, menor igual
	// seleciona somente com nota maior > 7
	List<Filme> findByNotaGreaterThan(Float nota); // > nota
	
	List<Filme> findByNotaGreaterThanEqual(Float nota); // >= nota
	
	List<Filme> findByNotaLessThanEqual(Float nota); // <= nota
	
	// Seleciona todos os filmes, porem limita o resultado até 3 registros "Top3"
	// e aplica filtro de nota ser maior que, e ordena por nota Desc
	
	List<Filme> findTop3ByNotaGreaterThanEqualOrderByNotaDesc(Float nota); // >= nota
	
	// busca por intervalo, define um valor minimo e um valor maximo
	List<Filme> findByNotaBetween(Float min, Float max);
	
	// "exists" é usado para realizar um teste, retorna true ou false
	// nesse caso verificamos se existe algum filme com uma categoria x 
	boolean existsFilmeByCategoria(String categoria);
	
	/**
	 * NAMED QUERY
	 * ------------
	 * Com Named query podemos escrever um tipo de "sql" em JPQL,
	 * Java Persistence Query Language.
	 * Usar a annotation @Query e passar a query como parametro 
	 */
	@Query("select f from Filme f where categoria = ?1")
	List<Filme> selecionaFilmePorCategoria(String categoria);
	
	
}
