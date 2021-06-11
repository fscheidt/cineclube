package br.com.cineclube.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.dao.CategoriaRepository;
import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Categoria;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;
import br.com.cineclube.tmdb.model.MovieTMDB;
import br.com.cineclube.tmdb.service.MoviedbService;

@RestController
@RequestMapping("/api")
@CrossOrigin() // desabilita CORS policy
public class ApiController {
	
	@Autowired
	PessoaRepository dao;
	
	@Autowired
	FilmeRepository daof;
	
	@Autowired
	MoviedbService apiService;
	
	@Autowired
	CategoriaRepository daoCat;
	
	@GetMapping("categorias")
    public List<Categoria> getCategorias(@RequestParam(value = "q", required = false) String query) {
        if (!StringUtils.hasLength(query)) {
            return daoCat.findAll();
        }
        return daoCat.findByNomeIgnoreCaseContaining(query);
    }
			
//	http://localhost:8080/api/elenco?q=ja
	@GetMapping("/elenco")
	public List<Pessoa> pessoasElenco(@RequestParam(value = "q", required = false) String query) {
		if (!StringUtils.hasLength(query)) {
			return dao.findAll();
		}
		return dao.findByNomeIgnoreCaseContaining(query);
	}
	/* 
	 * API FILME 
	 * 
	 * */
	// Get ALL
	// `http :8080/api/filmes`
	@GetMapping(value = "/filmes") 
	Iterable<Filme> getFilmes() { 
		return daof.findAll();
	}
	
	@GetMapping("/filme/{id}")
	Filme getFilme(@PathVariable Long id) {
		
		Filme filme = daof.findById(id).get();
		
		MovieTMDB moviedb = apiService.searchOneMovie(
				filme.getNome(), 
				filme.getLancamento().getYear()
		);
		filme.setMoviedb(moviedb);
		
		return filme;
	}
	
	/* 
	 * API PESSOA 
	 * 
	 * */
	// READ
	// `http :8080/api/pessoa/1`
	// `http GET :8080/api/pessoa/1`
	// optional trata NPE
	@GetMapping("/pessoa/{id}")
	Optional<Pessoa> getPessoa(@PathVariable Long id) { 
		return dao.findById(id);
	}
	// Get ALL
	// `http :8080/api/pessoas`
	@GetMapping(value = "/pessoas") 
	Iterable<Pessoa> gePessoas() { 
		return dao.findAll();
	}
	// CREATE
	// http :8080/api/pessoa < pessoa_frank.json
	@PostMapping("/pessoa")
	Pessoa postPessoa(@RequestBody Pessoa pessoa) {
		dao.save(pessoa);
		return pessoa;
	}
	
	// UPDATE
	/**
	 * `http PUT :8080/api/pessoa/4 < pessoa_kate.json`
	 * `http PUT :8080/api/pessoa/111 < pessoa_maria.json`
	 */
	@PutMapping("/pessoa/{id}")
	ResponseEntity<Pessoa> putPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa p = dao.save(pessoa);
		if(p!=null)
			return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
		
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
	
	// DELETE
	/**
    * DELETE == DELETE
    * `http DELETE :8080/api/pessoa/7`
    */
   @DeleteMapping("/pessoa/{id}")
   void deletePessoa(@PathVariable Long id) {
       dao.removerPessoa(id);
   }
	
}
