package br.com.stefanini.desafio.controller.dto;

public class RelatorioProcessosDTO {
	
	private Integer publicos;
	private Integer privados;
	private Integer emAndamento;
	private Integer julgados;
	private Integer arquivados;
	
	public RelatorioProcessosDTO() {}

	public RelatorioProcessosDTO(Integer publicos, Integer privados, Integer emAndamento, Integer julgados, Integer arquivados) {
		this.publicos = publicos;
		this.privados = privados;
		this.emAndamento = emAndamento;
		this.julgados = julgados;
		this.arquivados = arquivados;
	}

	public Integer getPublicos() {
		return publicos;
	}

	public Integer getPrivados() {
		return privados;
	}

	public Integer getEmAndamento() {
		return emAndamento;
	}

	public Integer getJulgados() {
		return julgados;
	}

	public Integer getArquivados() {
		return arquivados;
	}
	
}
