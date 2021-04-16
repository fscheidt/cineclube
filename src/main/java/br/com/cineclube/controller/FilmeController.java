package br.com.cineclube.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.model.Category;
import br.com.cineclube.model.Filme;

@Controller
@RequestMapping("/filmes") // http://localhost:8080/filmes
public class FilmeController {
	
	@Autowired // delega ao spring a criacao do repository
	FilmeRepository dao;
	
	// http://localhost:8080/filmes/new
	@RequestMapping("/new")
	public String newForm(Model model) {
		Filme filme = new Filme();
		model.addAttribute("categories", Category.values());
		model.addAttribute("filme", filme);
		return "filme/manterFilme.html";
	}
	@GetMapping(value = "/delete/{id}") // http://localhost:8080/filmes/delete/10
	public String delete(@PathVariable Long id) {
		dao.deleteById(id);
		return "redirect:/filmes/list";
	}
	@GetMapping(value = "/edit/{id}") // http://localhost:8080/filmes/edit/10
	public String edit(@PathVariable Long id, Model model) {
		Filme filme = dao.getOne(id);
		
		model.addAttribute("filme", filme);
		model.addAttribute("categories", Category.values());
		return "filme/manterFilme.html";
	}
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<Filme> filmeList = dao.findAll();
		
		// exemplo de many-to-many - usando Named Query com join
//		List<Filme> filmeList = dao.buscaPorPessoaAndCategoria("Jake Skin", Category.SCIFI.name());
		
//		List<Filme> filmeList = dao.findByCategoria("DRAMA");
		
//		List<Filme> filmeList = dao.findByNomeAndCategoria("Avatar", "ACTION");
		
//		List<Filme> filmeList = dao.findByOrderByCategoriaDesc();
		
//		List<Filme> filmeList = dao.findByNotaGreaterThanEqual(7f);
		
//		List<Filme> filmeList = dao.findTop3ByNotaGreaterThanEqualOrderByNotaDesc(7f);
				
		// Entre valores (nota 7 e nota 9)
//		List<Filme> filmeList = dao.findByNotaBetween(7f, 9f);
		
//		List<Filme> filmeList = dao.selecionatFilmePorCategoria("DRAMA");
		
		
		model.addAttribute("categories", Category.values());
		model.addAttribute("filmeList", filmeList);
		model.addAttribute("category", "Selecionar");
		return "filme/list.html";
	}
	@PostMapping("/save") // usar @Valid para validar o objeto filme
	public String save(@Valid Filme filme, BindingResult result, Model model) {
		if(result.hasErrors()) { // se possui algum erro retorna ao formulario
			model.addAttribute("categories", Category.values());
			return "filme/manterFilme.html";
		}
		// se tudo ok, entao salva no db:
		dao.save(filme);
		return "redirect:/filmes/list"; // redireciona para /filmes/list
	}
	@PostMapping(value = "/category")
	public String filtra(String cat, Model model) {
		List<Filme> filmes = dao.findByCategoria(cat);
		model.addAttribute("filmeList",filmes);
		model.addAttribute("category",cat);
		model.addAttribute("categories", Category.values());
		return "filme/list.html";
	}
	
}
