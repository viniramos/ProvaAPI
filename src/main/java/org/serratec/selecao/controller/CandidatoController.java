package org.serratec.selecao.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.selecao.dto.CandidatoDto;
import org.serratec.selecao.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidados")
public class CandidatoController {
	
	@Autowired
	private CandidatoService servico;

	@GetMapping
	public List<CandidatoDto> obterTodos() {
		return servico.obterTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CandidatoDto> obterPorId(@PathVariable Long id) {
		Optional<CandidatoDto> dto = servico.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@GetMapping("/nome/{nome}")
	public List<CandidatoDto> obterPorNome(@PathVariable String nome) {
		return servico.obterPorNome(nome);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoDto salvarCurriculo(@RequestBody CandidatoDto dto) {
		return servico.salvarCurriculo(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCurriculo(@PathVariable Long id){
		if(!servico. apagarCurriculo(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CandidatoDto> alterarCurriculo(@PathVariable Long id, @RequestBody CandidatoDto dto){
		Optional<CandidatoDto> curriculoAlterado = servico.alterarCurriculo(id, dto);
		if (!curriculoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(curriculoAlterado.get());
	}
}
	

