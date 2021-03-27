package br.com.orangeTalents.aplicaVacinas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.orangeTalents.aplicaVacinas.model.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long>{

}
