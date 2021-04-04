package br.com.stefanini.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.stefanini.desafio.controller.dto.RelatorioProcessosDTO;
import br.com.stefanini.desafio.repository.RelatorioRepository;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {
	
	@Autowired
	private RelatorioRepository relatorioRepository;

	@GetMapping("/processos")
	public RelatorioProcessosDTO relatorioProcessos() {
		RelatorioProcessosDTO relatorio = relatorioRepository.retornarRelatorioDosProcessos();
		
		return relatorio;
	}
}
