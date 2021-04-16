package br.com.cineclube.model;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message="Nome campo obrigatorio")
	@Size(min=1, max=50, message="Minimo de {min} caracteres em maximo de {max}")
	@Column(nullable = false)
	private String nome;
	
	@NotNull
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate lancamento;
	
	@NotBlank
	private String categoria;
	
	private Float nota;
	
	@ManyToMany
	@JoinTable(name="filme_pessoa",
	joinColumns = {@JoinColumn(name="filme_id")},
	inverseJoinColumns = {@JoinColumn(name="pessoa_id")})
	private Set<Pessoa> pessoas;
	
	public Filme() {}

	public Filme(String nome, Float nota, LocalDate lancamento, String categoria) {
		this.nome = nome;
		this.lancamento = lancamento;
		this.nota = nota;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
