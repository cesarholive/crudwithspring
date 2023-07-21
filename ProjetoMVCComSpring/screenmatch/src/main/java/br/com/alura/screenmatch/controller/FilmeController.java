package br.com.alura.screenmatch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.screenmatch.domain.filme.DadosAlteracaoFilme;
import br.com.alura.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.alura.screenmatch.domain.filme.Filme;
import br.com.alura.screenmatch.domain.filme.FilmeRepository;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	private FilmeRepository repository;
	
	@GetMapping("/formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if(id != null) {
			var filme = repository.getReferenceById(id);
			model.addAttribute("filme", filme);
		}
		return "filmes/formulario"; 
	}     
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll()); // enviar váriavel para a página html. assim conseguindo imprimir na tabela
		return "filmes/listagem";            // foi utilizada a classe fo spring chamada Model na mesma é passado o nome da lista no html 
	}                                        // e o nome da lista local.   
	
	@PostMapping
	@Transactional
	public String cadastraFilme(DadosCadastroFilme dados) {
		var filme = new Filme(dados);
		repository.save(filme);
		
		return "redirect:/filmes"; // redirect para o /filmes assim invocando o método do carregaPagina 
	}
	
	@PutMapping
	@Transactional // abrindo transação com o filme
	public String alteraFilme(DadosAlteracaoFilme dados) {
		var filme = repository.getReferenceById(dados.id()); // carregando o filme de dentro do banco de dados
		filme.atulizaDados(dados);
		
		return "redirect:/filmes"; // redirect para o /filmes assim invocando o método do carregaPagina 
	}
	 
	@DeleteMapping
	@Transactional
	public String removeFilme(Long id) {
		repository.deleteById(id);
		return "redirect:/filmes";
	}
	 
}      
           