package br.com.stefanini.desafio.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import br.com.stefanini.desafio.model.Processo;
import br.com.stefanini.desafio.model.SituacaoProcesso;

public class AtualizacaoProcessoForm {
	
	@NotBlank
	@Pattern(regexp = "[0-9]{6}-?[0-9]{2}.?[0-9]{4}.?[0-9]{1}.?[0-9]{2}.?[0-9]{4}")
	private String numero;
	@NotNull
	private boolean segredoDeJustica;
	@NotNull
	private SituacaoProcesso situacao;
	@NotNull @Min(1)
	private int quantidadePartes;
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public boolean isSegredoDeJustica() {
		return segredoDeJustica;
	}
	
	public void setSegredoDeJustica(boolean segredoDeJustica) {
		this.segredoDeJustica = segredoDeJustica;
	}
	
	public SituacaoProcesso getSituacao() {
		return situacao;
	}
	
	public void setSituacao(SituacaoProcesso situacao) {
		this.situacao = situacao;
	}
	
	public int getQuantidadePartes() {
		return quantidadePartes;
	}
	
	public void setQuantidadePartes(int quantidadePartes) {
		this.quantidadePartes = quantidadePartes;
	}

	public Processo atualizar(Processo processo) {
		processo.setNumero(this.numero);
		processo.setSegredoDeJustica(this.segredoDeJustica);
		processo.setSituacao(this.situacao);
		processo.setQuantidadePartes(this.quantidadePartes);
		
		return processo;
	}

}
