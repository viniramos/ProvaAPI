package org.serratec.selecao.repository;

import java.util.List;

import org.serratec.selecao.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

	List<Candidato> findByNomeIgnoreCase(String nome);
	
}