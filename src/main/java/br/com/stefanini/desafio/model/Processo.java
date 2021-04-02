package br.com.stefanini.desafio.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Processo {
	
	@Id @Column(length = 24)
	private String numero;
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	@Column(nullable = false)
	private boolean publico;
	@Enumerated(EnumType.STRING)
	private SituacaoProcesso situacao = SituacaoProcesso.EM_ANDAMENTO;
	@Column(name = "quantidade_partes", nullable = false)
	private int quantidadePartes;
	
	public Processo() {}
	
	public Processo(String numero, boolean publico, int quantidadePartes) {
		this.numero = numero;
		this.publico = publico;
		this.quantidadePartes = quantidadePartes;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String dataAtual = LocalDateTime.now().format(formatter);
		
		this.dataCadastro = LocalDateTime.parse(dataAtual, formatter);
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public boolean isPublico() {
		return publico;
	}
	
	public void setPublico(boolean publico) {
		this.publico = publico;
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

}
