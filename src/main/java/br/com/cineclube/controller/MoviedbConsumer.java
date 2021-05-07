package br.com.cineclube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.model.FilmeDB;

@RestController
@RequestMapping("/api/v1")
public class MoviedbConsumer {
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
    
    @RequestMapping("/filmedb/{id}")
    public FilmeDB getFilmeById(@PathVariable Long id) {
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/movie/" + id + "?api_key=" +  apiKey;
        FilmeDB filme = apiRequest.getForObject(filmeUrl, FilmeDB.class);
        return filme;
    }
}
