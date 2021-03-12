package br.com.cineclube.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import br.com.cineclube.model.Category;
import br.com.cineclube.model.Filme;

//Service => anotacao que identifica que a classe deve ser detectada durante a fase de scanning do classpath
@Service
public class DataLoaderHelper {
	
	public static void loadData(FilmeRepository daof /*, PessoaRepository daop*/) {
		
		List<Filme> filmeList = new ArrayList<>();
		filmeList.add(new Filme("Avatar", 	7f, 	2009, Category.ACTION.name()));
		filmeList.add(new Filme("Matrix", 	9f, 	1999, Category.SCIFI.name()));
		filmeList.add(new Filme("Terminator",8f, 	1984, Category.SCIFI.name()));
		filmeList.add(new Filme("Rock", 	6f, 	1976, Category.ACTION.name()));
		filmeList.add(new Filme("Titanic", 	4f, 	1997, Category.DRAMA.name()));
		filmeList.add(new Filme("Alien", 	10f, 	1979, Category.SCIFI.name()));
		daof.saveAll(filmeList);
		
//		List<Pessoa> pessoaList = new ArrayList<>();
//		pessoaList.add(new Pessoa("Leonard"));
//		pessoaList.add(new Pessoa("Jake"));
//		pessoaList.add(new Pessoa("Arnold"));
//		pessoaList.add(new Pessoa("Kate"));
//		pessoaList.add(new Pessoa("Anne"));
//		daop.saveAll(pessoaList);
	}
	// @Bean => indica que o metodo loader gera um Bean gerenciado pelo Spring container.
	// CommandLineRunner => indica que deve ser executado pelo SpringApplication.
	@Bean
	public CommandLineRunner loader(FilmeRepository daof/*, PessoaRepository daop*/) {
		return (args) -> {
			DataLoaderHelper.loadData(daof/*, daop*/);
		};
	}

}
