package br.com.orangeTalents.aplicaVacinas.controller.dto.vacina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.orangeTalents.aplicaVacinas.model.Vacina;
import br.com.orangeTalents.aplicaVacinas.repository.VacinaRepository;

public class AtualizaVacinaDto {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@NotBlank
	private String nomeVacina;

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotBlank
	private String dataVacinacao;

	public String getNomeVacina() {
		return nomeVacina;
	}

	public String getDataVacinacao() {
		return dataVacinacao;
	}
	
	public Vacina atualizar(Long id, VacinaRepository vacinaRepository) {
		Vacina vacina = vacinaRepository.getOne(id);

		vacina.setNome(this.nomeVacina);
		vacina.setDataVacinacao(LocalDate.parse(this.dataVacinacao, formatter));

		return vacina;
	}
}
