package br.com.stefanini.desafio.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.com.stefanini.desafio.controller.dto.RelatorioProcessosDTO;

@Repository
public class RelatorioRepository {
	
	@Autowired
	private EntityManager em;
	
	public RelatorioProcessosDTO retornarRelatorioDosProcessos() {
		StoredProcedureQuery query = em.createStoredProcedureQuery("getRelatorioProcessos");
		query.registerStoredProcedureParameter("p_publicos", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_privados", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_em_andamento", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_julgados", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_arquivados", Integer.class, ParameterMode.OUT);
		
		query.execute();
		
		Integer quantPublicos = (Integer) query.getOutputParameterValue("p_publicos");
		Integer quantPrivados = (Integer) query.getOutputParameterValue("p_privados");
		Integer quantEmAndamento = (Integer) query.getOutputParameterValue("p_em_andamento");
		Integer quantJulgados = (Integer) query.getOutputParameterValue("p_julgados");
		Integer quantArquivados = (Integer) query.getOutputParameterValue("p_arquivados");
		
		RelatorioProcessosDTO relatorioDTO = new RelatorioProcessosDTO(quantPublicos, quantPrivados, quantEmAndamento, quantJulgados, quantArquivados);
		
		return relatorioDTO;
	}

}
