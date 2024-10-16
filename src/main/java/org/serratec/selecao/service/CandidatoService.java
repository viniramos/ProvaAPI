package org.serratec.selecao.service;

import java.util.List;
import java.util.Optional;

import org.serratec.selecao.dto.CandidatoDto;
import org.serratec.selecao.model.Candidato;
import org.serratec.selecao.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
	@Autowired
	private  CandidatoRepository repositorio;
	
	public List<CandidatoDto> obterTodos(){
		return repositorio.findAll().stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
	
	public Optional<CandidatoDto> obterPorId(Long id){
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(CandidatoDto.toDto(repositorio.findById(id).get()));
	}
	
	public CandidatoDto salvarCurriculo(CandidatoDto dto) {
		Candidato candidatoEntity = repositorio.save(dto.toEntity());
		return CandidatoDto.toDto(candidatoEntity);
	}
	
	public boolean apagarCurriculo(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}

	public Optional<CandidatoDto> alterarCurriculo(Long id, CandidatoDto dto){
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Candidato candidatoEntity = dto.toEntity();
		candidatoEntity.setId(id);
		repositorio.save(candidatoEntity);
		return Optional.of(CandidatoDto.toDto(candidatoEntity));
	}
	
	public List<CandidatoDto> obterPorNome(String nome) {
		List<Candidato> candidatos = repositorio.findByNomeIgnoreCase(nome);
		return candidatos.stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
    
}	
	


