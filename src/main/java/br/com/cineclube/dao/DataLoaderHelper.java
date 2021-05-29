package br.com.cineclube.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import br.com.cineclube.model.Categoria;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;

@Service
public class DataLoaderHelper {
	
	public static void loadData(
			FilmeRepository daof, 
			PessoaRepository daop, 
			CategoriaRepository daoCat) {
		
		List<Categoria> categoriaList = new ArrayList<>();
		categoriaList.add(new Categoria("Action"));
		categoriaList.add(new Categoria("Adventure"));
		categoriaList.add(new Categoria("Animation"));
		categoriaList.add(new Categoria("Biography"));
		categoriaList.add(new Categoria("Comedy"));
		categoriaList.add(new Categoria("Crime"));
		categoriaList.add(new Categoria("Drama"));
		categoriaList.add(new Categoria("Documentary"));
		categoriaList.add(new Categoria("Fantasy"));
		categoriaList.add(new Categoria("Horror"));
		categoriaList.add(new Categoria("Mistery"));
		categoriaList.add(new Categoria("Romance"));
		categoriaList.add(new Categoria("Sci-fi"));
		daoCat.saveAll(categoriaList);
		
		// teste do relacionamento N-N (filme-categoria) para o filme Avatar
		List<Categoria> multiplasCategorias = new ArrayList<>();
		multiplasCategorias.add(daoCat.findByNome("Action"));
		multiplasCategorias.add(daoCat.findByNome("Sci-fi"));
		multiplasCategorias.add(daoCat.findByNome("Drama"));
		
		List<Filme> filmeList = new ArrayList<>();
		filmeList.add(new Filme("Avatar", 	7f, 	LocalDate.of(2009, 1, 28), multiplasCategorias));
		filmeList.add(new Filme("Matrix", 	9f, 	LocalDate.of(1999, 1, 1), daoCat.findByNome("Sci-fi")));
		filmeList.add(new Filme("Terminator",8f, 	LocalDate.of(1984, 1, 1), daoCat.findByNome("Sci-fi")));
		filmeList.add(new Filme("Rock", 	6f, 	LocalDate.of(1976, 1, 1), daoCat.findByNome("Action")));
		filmeList.add(new Filme("Titanic", 	4f, 	LocalDate.of(1997, 1, 1), daoCat.findByNome("Drama")));
		filmeList.add(new Filme("Alien", 	10f, 	LocalDate.of(1979, 1, 1), daoCat.findByNome("Sci-fi")));
		filmeList.add(new Filme("Chernobyl", 		9.40f, 	LocalDate.of(2019, 1, 21), daoCat.findByNome("Sci-fi")));
		filmeList.add(new Filme("Breaking Bad", 	10f, 	LocalDate.of(2008, 1, 21), daoCat.findByNome("Crime")));
		filmeList.add(new Filme("Game of Thrones", 	9.3f, 	LocalDate.of(2011, 1, 21), daoCat.findByNome("Action")));
		filmeList.add(new Filme("Star Wars: Episode I", 	6.5f, 	LocalDate.of(1999, 1, 21), daoCat.findByNome("Sci-fi")));
		filmeList.add(new Filme("The Thirteenth Floor", 	7.10f, 	LocalDate.of(1999, 1, 21), daoCat.findByNome("Sci-fi")));
		daof.saveAll(filmeList);
		
		List<Pessoa> pessoaList = new ArrayList<>();
		pessoaList.add(new Pessoa("Leonardo DiCaprio", LocalDate.of(1974, 11, 11)));
		pessoaList.add(new Pessoa("Jake Skin", 	 LocalDate.of(1999, 11, 28)));
		pessoaList.add(new Pessoa("Arnold Shuartz",  LocalDate.of(1962, 11, 15)));
		pessoaList.add(new Pessoa("Kate Blan", 	 LocalDate.of(2008, 5, 1)));
		pessoaList.add(new Pessoa("Anne Silver",	 LocalDate.of(1981, 6, 20)));
		pessoaList.add(new Pessoa("Athena Greek",	 LocalDate.of(2012, 8, 10)));
		pessoaList.add(new Pessoa("Artemis Greek",	 LocalDate.of(1980, 1, 1)));
		
		pessoaList.add(new Pessoa("Sam Worthington", LocalDate.of(1976, 8, 2)));
		pessoaList.add(new Pessoa("Laurence Fishburne", LocalDate.of(1961, 7, 30)));
		
		daop.saveAll(pessoaList);
		
		/**
		 * ADICIONAR elenco de atores para os filmes:
		 * */
		
		Set<Pessoa> elencoAvatar = new HashSet<>();
		elencoAvatar.add(daop.findById(1L).get());
		elencoAvatar.add(daop.findById(2L).get());
		elencoAvatar.add(daop.findById(8L).get());
		Filme avatar = daof.findById(1L).get();
		avatar.setPessoas(elencoAvatar);
		daof.save(avatar);
		
		Filme matrix = daof.findById(2L).get();
		Set<Pessoa> elencoMatrix = new HashSet<>();
		elencoMatrix.add(daop.findById(1L).get());
		elencoMatrix.add(daop.findById(3L).get());
		elencoMatrix.add(daop.findById(2L).get());
		elencoMatrix.add(daop.findById(9L).get());
		matrix.setPessoas(elencoMatrix);
		daof.save(matrix);
		
		Filme alien = daof.findById(5L).get();
		Set<Pessoa> elencoAlien = new HashSet<>();
		elencoAlien.add(daop.findById(2L).get());
		alien.setPessoas(elencoAlien);
		daof.save(alien);
		
	}
	@Bean
	public CommandLineRunner loader(FilmeRepository daof, PessoaRepository daop, CategoriaRepository daoCat) {
		return (args) -> {
			DataLoaderHelper.loadData(daof, daop, daoCat);
		};
	}
}
