package br.com.aplicaVacinas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aplicaVacinas.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Boolean existsByEmail(String email);
	
	Boolean existsByCpf(String cpf);
	
	List<Usuario> findByCpf(String cpf);
	
}
