package br.com.cineclube.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import br.com.cineclube.model.Category;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;

//Service => anotacao que identifica que a classe deve ser detectada durante a fase de scanning do classpath
@Service
public class DataLoaderHelper {
	
	public static void loadData(FilmeRepository daof , PessoaRepository daop) {
		
		List<Filme> filmeList = new ArrayList<>();
		filmeList.add(new Filme("Avatar", 	7f, 	LocalDate.of(2009, 1, 28), Category.ACTION.name()));
		filmeList.add(new Filme("Avatar", 	8f, 	LocalDate.of(2009, 1, 28), Category.DRAMA.name()));
		filmeList.add(new Filme("Matrix", 	9f, 	LocalDate.of(1999, 1, 1), Category.SCIFI.name()));
		filmeList.add(new Filme("Terminator",8f, 	LocalDate.of(1984, 1, 1), Category.SCIFI.name()));
		filmeList.add(new Filme("Rock", 	6f, 	LocalDate.of(1976, 1, 1), Category.ACTION.name()));
		filmeList.add(new Filme("Titanic", 	4f, 	LocalDate.of(1997, 1, 1), Category.DRAMA.name()));
		filmeList.add(new Filme("Alien", 	10f, 	LocalDate.of(1979, 1, 1), Category.SCIFI.name()));
		filmeList.add(new Filme("Chernobyl", 		9.40f, 	LocalDate.of(2019, 1, 21), Category.SCIFI.name()));
		filmeList.add(new Filme("Terminator",		8.11f, 	LocalDate.of(1984, 1, 21), Category.SCIFI.name()));
		filmeList.add(new Filme("Breaking Bad", 	10f, 	LocalDate.of(2008, 1, 21), Category.CRIME.name()));
		filmeList.add(new Filme("Game of Thrones", 	9.3f, 	LocalDate.of(2011, 1, 21), Category.ACTION.name()));
		filmeList.add(new Filme("Star Wars: Episode I", 	6.5f, 	LocalDate.of(1999, 1, 21), Category.SCIFI.name()));
		filmeList.add(new Filme("The Thirteenth Floor", 	7.10f, 	LocalDate.of(1999, 1, 21), Category.SCIFI.name()));
		daof.saveAll(filmeList);
		
		daof.saveAll(filmeList);
		
		List<Pessoa> pessoaList = new ArrayList<>();
		pessoaList.add(new Pessoa("Leonard Skin", LocalDate.of(1944, 4, 8)));
		pessoaList.add(new Pessoa("Jake Skin", 	 LocalDate.of(1999, 11, 28)));
		pessoaList.add(new Pessoa("Arnold Shuartz",  LocalDate.of(1962, 11, 15)));
		pessoaList.add(new Pessoa("Kate Blan", 	 LocalDate.of(2008, 5, 1)));
		pessoaList.add(new Pessoa("Anne Silver",	 LocalDate.of(1981, 6, 20)));
		pessoaList.add(new Pessoa("Athena Greek",	 LocalDate.of(2012, 8, 10)));
		pessoaList.add(new Pessoa("Artemis Greek",	 LocalDate.of(1980, 1, 1)));
		daop.saveAll(pessoaList);
		
		/**
		 * ADICIONAR elenco de atores para os filmes:
		 * */
		/* 
		Set<Pessoa> elencoAvatar = new HashSet<>();
		elencoAvatar.add(daop.findById(1L).get());
		elencoAvatar.add(daop.findById(2L).get());		
		Filme avatar = daof.findById(1L).get();
		avatar.setPessoas(elencoAvatar);		
		daof.save(avatar);
		
		Filme matrix = daof.findById(2L).get();
		Set<Pessoa> elencoMatrix = new HashSet<>();
		elencoMatrix.add(daop.findById(1L).get());
		elencoMatrix.add(daop.findById(3L).get());
		elencoMatrix.add(daop.findById(2L).get()); // jake skin
		matrix.setPessoas(elencoMatrix);
		daof.save(matrix);
		
		Filme alien = daof.findById(5L).get();
		Set<Pessoa> elencoAlien = new HashSet<>();
		elencoAlien.add(daop.findById(2L).get()); // jake skin
		alien.setPessoas(elencoAlien);
		daof.save(alien);
		
		*/
		
	}
	// @Bean => indica que o metodo loader gera um Bean gerenciado pelo Spring container.
	// CommandLineRunner => indica que deve ser executado pelo SpringApplication.
	@Bean
	public CommandLineRunner loader(FilmeRepository daof, PessoaRepository daop) {
		return (args) -> {
			DataLoaderHelper.loadData(daof, daop);
		};
	}

}
