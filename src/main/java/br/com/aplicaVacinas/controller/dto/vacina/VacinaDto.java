package br.com.aplicaVacinas.controller.dto.vacina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.aplicaVacinas.model.Vacina;

public class VacinaDto {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Long usuarioId;

	@NotBlank
	private String nomeVacina;

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data precisa estar no formato dd/MM/yyyy")
	@NotBlank
	private String dataVacinacao;

	public VacinaDto(Vacina vacina) {
		this.usuarioId = vacina.getUsuario().getId();
		this.nomeVacina = vacina.getNome();
		this.dataVacinacao = vacina.getDataVacinacao().format(formatter);
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public String getDataVacinacao() {
		return dataVacinacao;
	}

	public Vacina toVacina() {
		Vacina vacina = new Vacina();
		vacina.setNome(this.nomeVacina);
		vacina.setDataVacinacao(LocalDate.parse(this.dataVacinacao, formatter));

		return vacina;
	}

}
