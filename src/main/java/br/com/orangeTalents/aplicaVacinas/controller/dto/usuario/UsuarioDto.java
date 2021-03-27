package br.com.orangeTalents.aplicaVacinas.controller.dto.usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import br.com.orangeTalents.aplicaVacinas.config.validacao.custom.unique.Unique;
import br.com.orangeTalents.aplicaVacinas.model.Usuario;

public class UsuarioDto {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@NotBlank
	private String nome;

	@Unique(message = "E-mail cadastrado", columnName = "email")
	@Email(message = "E-mail inválido")
	@NotBlank
	private String email;

	@Unique(message = "Cpf cadastrado", columnName = "cpf")
	@CPF(message = "Cpf inválido")
	@NotBlank
	private String cpf;

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data precisa estar no formato dd/MM/yyyy")
	@NotBlank
	private String dataDeNascimento;

	public UsuarioDto() {}

	public UsuarioDto(String nome, String email, String cpf, String dataDeNascimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public UsuarioDto(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpf();
		this.dataDeNascimento = usuario.getDataDeNascimento().format(formatter);
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

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setCpf(this.cpf);
		usuario.setDataDeNascimento(LocalDate.parse(this.dataDeNascimento, formatter));

		return usuario;
	}

}
