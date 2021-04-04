package br.com.stefanini.desafio.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.stefanini.desafio.controller.dto.ProcessoDTO;
import br.com.stefanini.desafio.controller.form.AtualizacaoProcessoForm;
import br.com.stefanini.desafio.controller.form.ProcessoForm;
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
	public ResponseEntity<ProcessoDTO> buscar(@PathVariable String numero) {
		Optional<Processo> optional = processoRepository.findById(numero);
		
		if (optional.isPresent()) {
			return ResponseEntity.ok(new ProcessoDTO(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<ProcessoDTO> cadastrar(@RequestBody @Valid ProcessoForm form, UriComponentsBuilder uriBuilder) {
		Processo processo = form.toProcesso();
		
		processoRepository.save(processo);
		
		URI uri = uriBuilder.path("/processos/{id}").buildAndExpand(processo.getNumero()).toUri();
		return ResponseEntity.created(uri).body(new ProcessoDTO(processo));
	}
	
	@PutMapping("/{numero}")
	@Transactional
	public ResponseEntity<ProcessoDTO> atualizar(@PathVariable String numero, @RequestBody @Valid AtualizacaoProcessoForm form) {
		Optional<Processo> optional = processoRepository.findById(numero);
		
		if (optional.isPresent()) {
			Processo processo = form.atualizar(optional.get());
			
			return ResponseEntity.ok(new ProcessoDTO(processo));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> excluir(@PathVariable String numero) {
		Optional<Processo> optional = processoRepository.findById(numero);
		
		if (optional.isPresent()) {
			processoRepository.deleteById(numero);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/situacao/{situacao}")
	public ResponseEntity<List<ProcessoDTO>> buscarPelaSituacao(@PathVariable String situacao) {
		SituacaoProcesso situacaoEnum = null;
		try {
			situacaoEnum = SituacaoProcesso.valueOf(situacao);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		List<Processo> processos = processoRepository.findBySituacao(situacaoEnum);
		
		return ResponseEntity.ok(ProcessoDTO.converter(processos));
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
	
	@GetMapping("/buscarPelaData")
	public ResponseEntity<List<ProcessoDTO>> buscarPelaData(String data, String dataFinal) {
		LocalDate data1 = null;
		LocalDate data2 = null;
		
		if (data != null) {
			try {
				List<Integer> ints = Arrays.stream(data.split("-")).map(Integer::parseInt).collect(Collectors.toList());
				
				data1 = LocalDate.of(ints.get(0), ints.get(1), ints.get(2));
			} catch (Exception ex) {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
		
		if (dataFinal != null) {
			try {
				List<Integer> ints = Arrays.stream(dataFinal.split("-")).map(Integer::parseInt).collect(Collectors.toList());
				
				data2 = LocalDate.of(ints.get(0), ints.get(1), ints.get(2));
				
				List<Processo> processos = processoRepository.procurarPorIntervaloEntreDatas(data1, data2);
				return ResponseEntity.ok(ProcessoDTO.converter(processos));
			} catch (Exception ex) {
				return ResponseEntity.badRequest().build();
			}	
		}
	
		List<Processo> processos = null;
		try {
			processos = processoRepository.procurarPorData(data1);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		};
		
		return ResponseEntity.ok(ProcessoDTO.converter(processos));
	}
	
}
