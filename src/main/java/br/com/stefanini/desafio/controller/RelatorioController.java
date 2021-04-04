package br.com.stefanini.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<RelatorioProcessosDTO> relatorioProcessos() {
		RelatorioProcessosDTO relatorio = null;
		
		try {
			relatorio = relatorioRepository.retornarRelatorioDosProcessos();
		} catch (InvalidDataAccessResourceUsageException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(relatorio);
	}
}
