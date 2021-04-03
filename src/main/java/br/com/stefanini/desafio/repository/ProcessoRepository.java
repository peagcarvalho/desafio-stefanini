package br.com.stefanini.desafio.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.stefanini.desafio.model.Processo;
import br.com.stefanini.desafio.model.SituacaoProcesso;

public interface ProcessoRepository extends JpaRepository<Processo, String> {
	
	default List<Processo> procurarPorIntervaloEntreDatas(LocalDate dataInicial, LocalDate dataFinal) {
		return findByDataCadastroBetween(dataInicial.atStartOfDay(), dataFinal.plusDays(1).atStartOfDay());
	}
	
	default List<Processo> procurarPorData(LocalDate data) {
		return findByDataCadastroBetween(data.atStartOfDay(), data.plusDays(1).atStartOfDay());
	}
	
	List<Processo> findByDataCadastroBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	List<Processo> findBySegredoDeJustica(boolean segredoJustica);
	List<Processo> findBySituacao(SituacaoProcesso situacao);
	List<Processo> findBySegredoDeJusticaAndSituacao(boolean segredoJustica, SituacaoProcesso situacao);

}
