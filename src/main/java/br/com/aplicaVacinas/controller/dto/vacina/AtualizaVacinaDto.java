package br.com.aplicaVacinas.controller.dto.vacina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Pattern;

import br.com.aplicaVacinas.model.Vacina;
import br.com.aplicaVacinas.repository.VacinaRepository;

public class AtualizaVacinaDto {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private String nomeVacina;

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	private String dataVacinacao;

	public String getNomeVacina() {
		return nomeVacina;
	}

	public String getDataVacinacao() {
		return dataVacinacao;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public void setDataVacinacao(String dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}

	public Vacina atualizar(Long id, VacinaRepository vacinaRepository) {
		Vacina vacina = vacinaRepository.getOne(id);

		vacina.setNome(this.nomeVacina);
		vacina.setDataVacinacao(LocalDate.parse(this.dataVacinacao, formatter));

		return vacina;
	}
	
}
