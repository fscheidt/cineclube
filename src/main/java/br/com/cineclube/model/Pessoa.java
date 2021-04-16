package br.com.cineclube.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=3, max=50, message="Nome deve conter ao menos {min} caracteres")
	@Column(nullable = false)
	private String nome;
	
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private LocalDate dataNasc;
	
	@ManyToMany(mappedBy="pessoas")
	private Set<Filme> filmes;
	
	@Transient
	private Integer age;
	
	public Pessoa() {}
	
	public Pessoa(String nome, LocalDate dataNasc) {
		this.nome = nome;
		this.dataNasc = dataNasc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Integer getAge() {
		age = (int)ChronoUnit.YEARS.between(dataNasc, LocalDate.now());
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(Set<Filme> filmes) {
		this.filmes = filmes;
	}
	
}
