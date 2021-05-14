package br.com.cineclube.model;

import java.util.List;

public class WrapperMovieSearch {
	// representa o json retornado por essa url:
	// http https://api.themoviedb.org/3/search/movie?api_key=d1da20fbfa65312b857fb7b517bf855c query==avatar year==2009 
	/*
	 results: [
	 	{filme},
	 	{filme},
	 ]
	 * */
	
	private List<FilmeDB> results;

	public List<FilmeDB> getResults() {
		// ordem decrescente == ordena por filmes mais populares
    	results.sort( (f1,f2) -> Integer.compare(f2.getVote_count(), f1.getVote_count()) );
		return results;
	}

	public void setResults(List<FilmeDB> results) {
		this.results = results;
	}

}
