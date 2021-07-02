package br.com.orangeTalents.aplicaVacinas;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.aplicaVacinas.controller.dto.usuario.UsuarioDto;
import br.com.aplicaVacinas.model.Usuario;
import br.com.aplicaVacinas.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AplicaVacinasApplicationTests {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	@DisplayName("deveria inserir usuario e retornar status 201")
	public void insereUsuario() throws Exception {
		
		UsuarioDto novoUsuario = new UsuarioDto("John Manoe","johnmanoe@mail.com","26947175003","30/10/1998");
		
        UsuarioDto resultPost = given().port(8081).header("Content-Type", "application/json")
                .body(novoUsuario)
        .expect()
        	.statusCode(HttpStatus.CREATED.value())
        	.log().ifError()
        .when()
             .post("/usuarios").as(UsuarioDto.class);
		
		List<Usuario> optional = usuarioRepository.findByCpf(resultPost.getCpf());

		if (!optional.isEmpty()) {
			usuarioRepository.deleteById(optional.get(0).getId());
		}
		
	}
	
	@Test
	@DisplayName("deveria retornar status 400 por causa de campo vazio")
	public void insereUsuarioComCampoVazio() throws Exception {
		
		UsuarioDto novoUsuario = new UsuarioDto("John Manoe","","26947175003","30/10/1998");
		
		given().port(8081).header("Content-Type", "application/json")
                .body(novoUsuario)
        .expect()
        	.statusCode(HttpStatus.BAD_REQUEST.value())
        	.log().ifError()
        .when()
             .post("/usuarios");
	}

}
