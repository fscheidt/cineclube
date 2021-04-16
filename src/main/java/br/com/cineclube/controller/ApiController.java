package br.com.cineclube.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Pessoa;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	PessoaRepository dao;
	
//	http://localhost:8080/api/elenco?q=ja
	@GetMapping("/elenco")
	public List<Pessoa> pessoasElenco(@RequestParam(value = "q", required = false) String query) {
		if (!StringUtils.hasLength(query)) {
			return dao.findAll();
		}
		return dao.findByNomeIgnoreCaseContaining(query);
	}
}
