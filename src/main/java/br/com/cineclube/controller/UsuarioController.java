package br.com.cineclube.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.UsuarioRepository;
import br.com.cineclube.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController{

	@Autowired
	UsuarioRepository dao;
	
	@GetMapping("/new")
	public String newForm(Model model) {
		return "todo: implementar";
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		return "todo: implementar";
	}
	@PostMapping("/save")
	public String save(@Valid Usuario p, BindingResult result, Model model) {
		return "todo: implementar";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Usuario> usuarios = dao.findAll();
		model.addAttribute("userList", usuarios);
		return "usuario/list.html";
	}
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		return "todo: implementar";
	}
}

