package br.com.aplicaVacinas.controller.dto.usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.aplicaVacinas.model.Usuario;
import br.com.aplicaVacinas.repository.UsuarioRepository;

public class AtualizaUsuarioDto {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@NotBlank
	private String nome;

	@Email(message = "E-mail inválido")
	@NotBlank
	private String email;

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotBlank
	private String dataDeNascimento;

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);

		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setDataDeNascimento(LocalDate.parse(this.dataDeNascimento, formatter));

		return usuario;
	}
}
