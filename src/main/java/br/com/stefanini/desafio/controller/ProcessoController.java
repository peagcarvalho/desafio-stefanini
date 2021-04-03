package br.com.stefanini.desafio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.stefanini.desafio.controller.dto.ProcessoDTO;
import br.com.stefanini.desafio.model.Processo;
import br.com.stefanini.desafio.model.SituacaoProcesso;
import br.com.stefanini.desafio.repository.ProcessoRepository;

@RestController
@RequestMapping("/processos")
public class ProcessoController {
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	@GetMapping
	public List<ProcessoDTO> listar() {
		List<Processo> processos = processoRepository.findAll();
		
		return ProcessoDTO.converter(processos);
	}
	
	@GetMapping("/{numero}")
	public ProcessoDTO buscarPeloNumero(@PathVariable String numero) {
		Processo processo = processoRepository.findById(numero).get();
		
		return new ProcessoDTO(processo);
	}
	
	@GetMapping("/situacao/{situacao}")
	public List<ProcessoDTO> buscarPelaSituacao(@PathVariable SituacaoProcesso situacao) {
		List<Processo> processos = processoRepository.findBySituacao(situacao);
		
		return ProcessoDTO.converter(processos);
	}
	
	@GetMapping("/publicos")
	public List<ProcessoDTO> buscarProcessosPublicos() {
		List<Processo> processos = processoRepository.findBySegredoDeJustica(false);
		
		return ProcessoDTO.converter(processos);
	}
	
	@GetMapping("/privados")
	public List<ProcessoDTO> buscarProcessosPrivados() {
		List<Processo> processos = processoRepository.findBySegredoDeJustica(true);
		
		return ProcessoDTO.converter(processos);
	}
	
	@GetMapping("/buscarPorData")
	public List<ProcessoDTO> buscarPorData(String data, String dataFinal) {
		LocalDate data1 = null;
		LocalDate data2 = null;
		
		if (data != null) {
			String[] dataDividida = data.split("/");
			
			int dia = Integer.parseInt(dataDividida[0]);
			int mes = Integer.parseInt(dataDividida[1]);
			int ano = Integer.parseInt(dataDividida[2]);
			
			data1 = LocalDate.of(ano, mes, dia);
		}
		
		if (dataFinal != null) {
			String[] dataFinalDividida = dataFinal.split("/");
			
			int dia = Integer.parseInt(dataFinalDividida[0]);
			int mes = Integer.parseInt(dataFinalDividida[1]);
			int ano = Integer.parseInt(dataFinalDividida[2]);
			
			data2 = LocalDate.of(ano, mes, dia);
			
			List<Processo> processos = processoRepository.procurarPorIntervaloEntreDatas(data1, data2);
			return ProcessoDTO.converter(processos);
		}
		
		if (data == null && dataFinal == null) {
			return new ArrayList<ProcessoDTO>();
		}
		
		List<Processo> processos = processoRepository.procurarPorData(data1);
		return ProcessoDTO.converter(processos);
	}
	
}
