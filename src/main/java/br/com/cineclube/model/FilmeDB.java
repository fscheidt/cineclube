package br.com.cineclube.model;

// classe que espelha o retorno da requisicao a api themoviedb
public class FilmeDB {

	private Long id;
	private String title;
	private String overview;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	
}
