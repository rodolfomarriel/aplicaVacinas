package br.com.orangeTalents.aplicaVacinas.controller.dto.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.orangeTalents.aplicaVacinas.controller.dto.vacina.VacinaDto;
import br.com.orangeTalents.aplicaVacinas.model.Usuario;

public class DetalhaUsuarioDto {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private LocalDate dataDeNascimento;

	private List<VacinaDto> vacina;

	public DetalhaUsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpf();
		this.dataDeNascimento = usuario.getDataDeNascimento();
		this.vacina = new ArrayList<>();
		this.vacina.addAll(usuario.getVacina().stream().map(VacinaDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public List<VacinaDto> getVacina() {
		return vacina;
	}

}
