package br.com.orangeTalents.aplicaVacinas.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.orangeTalents.aplicaVacinas.config.validacao.ErroDeRequisicaoDto;
import br.com.orangeTalents.aplicaVacinas.controller.dto.usuario.AtualizaUsuarioDto;
import br.com.orangeTalents.aplicaVacinas.controller.dto.usuario.DetalhaUsuarioDto;
import br.com.orangeTalents.aplicaVacinas.controller.dto.usuario.UsuarioDto;
import br.com.orangeTalents.aplicaVacinas.model.Usuario;
import br.com.orangeTalents.aplicaVacinas.repository.UsuarioRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> retornarTodos() {
		return usuarioRepository.findAll();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto novoUsuario,
			UriComponentsBuilder uriBuilder) {

		Usuario usuario = novoUsuario.toUsuario();
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(novoUsuario);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhaUsuarioDto> buscar(@PathVariable Long id) throws NotFoundException {

		Optional<Usuario> usuarioaBuscado = usuarioRepository.findById(id);

		if (usuarioaBuscado.isPresent()) {
			return ResponseEntity.ok(new DetalhaUsuarioDto(usuarioaBuscado.get()));
		}

		return ResponseEntity.notFound().build();

	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaUsuarioDto atualizaUsuario) throws JsonProcessingException {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
		
		String email = atualizaUsuario.getEmail();
		if (!usuarioExistente.get().getEmail().equals(email) && usuarioRepository.existsByEmail(email)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDeRequisicaoDto("email", "E-mail já cadastrado"));
		}
		
		if (usuarioExistente.isPresent()) {
			Usuario usuario = atualizaUsuario.atualizar(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Usuario> remover(@PathVariable Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);

		if (optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}

}
