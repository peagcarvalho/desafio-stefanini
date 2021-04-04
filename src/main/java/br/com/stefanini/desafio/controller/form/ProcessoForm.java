package br.com.stefanini.desafio.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import br.com.stefanini.desafio.model.Processo;

public class ProcessoForm {
	
	@NotBlank
	@Pattern(regexp = "[0-9]{6}-?[0-9]{2}.?[0-9]{4}.?[0-9]{1}.?[0-9]{2}.?[0-9]{4}")
	private String numero;
	@NotNull
	private boolean segredoDeJustica;
	@NotNull @Min(1)
	private int quantidadePartes;
	
	public Processo toProcesso() {
		return new Processo(numero, segredoDeJustica, quantidadePartes);
	}
	
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
	
	public int getQuantidadePartes() {
		return quantidadePartes;
	}
	
	public void setQuantidadePartes(int quantidadePartes) {
		this.quantidadePartes = quantidadePartes;
	}

}
