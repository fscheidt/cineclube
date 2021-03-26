package br.com.cineclube.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Pessoa;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaRepository dao;
	
	@GetMapping("/list")
	public String list(Model model) {
		// retorna todas as pessoas na base
		model.addAttribute("pessoaList",dao.findAll());
		
		// somente pessoas com data de nascimento anterior a 1980
//		model.addAttribute("pessoaList",dao.findByDataNascBefore(LocalDate.of(1980, 1, 1)));
		
		// somente pessoas com data de nascimento superior a 1980
//		model.addAttribute("pessoaList",dao.findByDataNascAfter(LocalDate.of(1980, 1, 1)));
		
		return "pessoa/list.html";
	}
	@GetMapping("/new")
	public String newForm(Model model) {
		Pessoa p = new Pessoa();
		model.addAttribute("pessoa", p);
		return "pessoa/new.html";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.deleteById(id);
		return "redirect:/pessoas/list";
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Pessoa p = dao.findById(id).get();
		model.addAttribute("pessoa", p);
		return "pessoa/new.html";
	}
	@PostMapping("/save")
	public String save(@Valid Pessoa pessoa, BindingResult result, Model model) {
		if(result.hasErrors())
			return "pessoa/new.html";
		dao.save(pessoa);
		return "redirect:/pessoas/list";
	}
	
}
