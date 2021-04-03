package br.com.stefanini.desafio.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.com.stefanini.desafio.model.Processo;
import br.com.stefanini.desafio.model.SituacaoProcesso;

public class ProcessoDTO {
	
	private String numero;
	private LocalDateTime dataCadastro;
	private boolean segredoDeJustica;
	private SituacaoProcesso situacao;
	private int quantidadePartes;
	
	public ProcessoDTO(Processo processo) {
		this.numero = processo.getNumero();
		this.dataCadastro = processo.getDataCadastro();
		this.segredoDeJustica = processo.isSegredoDeJustica();
		this.situacao = processo.getSituacao();
		this.quantidadePartes = processo.getQuantidadePartes();
	}
	
	public static List<ProcessoDTO> converter(List<Processo> processos) {
		return processos.stream().map(ProcessoDTO::new).collect(Collectors.toList());
	}

	public String getNumero() {
		return numero;
	}
	
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	
	public boolean isSegredoDeJustica() {
		return segredoDeJustica;
	}
	
	public SituacaoProcesso getSituacao() {
		return situacao;
	}
	
	public int getQuantidadePartes() {
		return quantidadePartes;
	}

}
