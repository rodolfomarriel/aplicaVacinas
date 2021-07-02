package br.com.orangeTalents.aplicaVacinas.controller;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.orangeTalents.aplicaVacinas.controller.dto.vacina.AtualizaVacinaDto;
import br.com.orangeTalents.aplicaVacinas.controller.dto.vacina.VacinaDto;
import br.com.orangeTalents.aplicaVacinas.model.Usuario;
import br.com.orangeTalents.aplicaVacinas.model.Vacina;
import br.com.orangeTalents.aplicaVacinas.repository.UsuarioRepository;
import br.com.orangeTalents.aplicaVacinas.repository.VacinaRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/vacinas")
public class VacinaRestController {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private VacinaRepository vacinaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Vacina> retornarTodos() {
		return vacinaRepository.findAll();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VacinaDto> cadastrar(@RequestBody @Valid VacinaDto novaVacinacao, UriComponentsBuilder uriBuilder) {
		
		Optional<Usuario> usuarioBuscado = usuarioRepository.findById(novaVacinacao.getUsuarioId());

		if(!usuarioBuscado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Usuario usuario = usuarioBuscado.get();
				
		Vacina vacina = novaVacinacao.toVacina();
		vacina.setUsuario(usuario);
		usuario.getVacina().add(vacina);
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/vacinas/{id}").buildAndExpand(vacina.getId()).toUri();
		return ResponseEntity.created(uri).body(novaVacinacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vacina> buscar(@PathVariable Long id) throws NotFoundException {
	    
		Optional<Vacina> vacinaBuscado = vacinaRepository.findById(id);
		
		if(vacinaBuscado.isPresent()) {
			return ResponseEntity.ok(vacinaBuscado.get());
		}		
		
		return ResponseEntity.notFound().build();

	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VacinaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaVacinaDto atualizaVacina) {
		Optional<Vacina> optional = vacinaRepository.findById(id);
		
		if (optional.isPresent()) {
			Vacina vacinaTmp = optional.get();
			
			if(atualizaVacina.getNomeVacina() == null || atualizaVacina.getNomeVacina().isEmpty()) {
				atualizaVacina.setNomeVacina(vacinaTmp.getNome());
			}
			
			if(atualizaVacina.getDataVacinacao() == null || atualizaVacina.getDataVacinacao().isEmpty()) {
				atualizaVacina.setDataVacinacao(vacinaTmp.getDataVacinacao().format(formatter));
			}
			
			Vacina vacina = atualizaVacina.atualizar(id, vacinaRepository);
			return ResponseEntity.ok(new VacinaDto(vacina));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Vacina> remover(@PathVariable Long id) {
		Optional<Vacina> optional = vacinaRepository.findById(id);

		if (optional.isPresent()) {
			vacinaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}

}
