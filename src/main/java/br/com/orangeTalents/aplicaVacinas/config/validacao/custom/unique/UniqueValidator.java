package br.com.orangeTalents.aplicaVacinas.config.validacao.custom.unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.orangeTalents.aplicaVacinas.repository.UsuarioRepository;

public class UniqueValidator implements ConstraintValidator<Unique,String> {

	@Autowired
    private UsuarioRepository usuarioRepository;
	
	private String columnName = "";

    @Override
    public void initialize(Unique unique) {
        unique.message();
        columnName = unique.columnName();
    }

    @Override
    public boolean isValid(String atributo, ConstraintValidatorContext context) {
    	
    	if (usuarioRepository != null && (columnName.equals("email") || columnName.equals("cpf"))) {
    		
    		if (usuarioRepository.existsByEmail(atributo)) {
                return false;
            }
            
            if (usuarioRepository.existsByCpf(atributo)) {
                return false;
            }
            
    	}        
        
        return true;
    }
    
}
