package org.serratec.selecao.dto;

import java.time.LocalDate;

import org.serratec.selecao.model.Candidato;

public record CandidatoDto(Long id, String nome, LocalDate anoNascimento, String cpf) {

	public Candidato toEntity() {
		Candidato candidato = new Candidato();
		candidato.setId(this.id);
		candidato.setNome(this.nome);
		candidato.setDataNascimento(this.anoNascimento);
		candidato.setCpf(this.cpf);
		return candidato;
	}

	public static CandidatoDto toDto(Candidato candidato) {
		return new CandidatoDto(candidato.getId(), candidato.getNome(), candidato.getDataNascimento(),
				candidato.getCpf());
	}
}
