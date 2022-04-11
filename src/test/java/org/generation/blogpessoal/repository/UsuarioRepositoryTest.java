package org.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Para rodar em uma porta randomica
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Para instanciar e realziar o teste unitário
public class UsuarioRepositoryTest 
{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll // O que fazer antes de testar...
	void start() 
	{
		usuarioRepository.save(new Usuario(0L, "DJ Cleiton Rasta","cleitinho@pedra.com","cabecadegelo","https://i.imgur.com/FETvs2O.jpg"));
		usuarioRepository.save(new Usuario(0L, "DJ Laurinha Lero","lari@pedra.com","lagelo","https://i.imgur.com/FETvs2O.jpg"));
		usuarioRepository.save(new Usuario(0L, "Ednaldo Pereira","ednaldo@pedra.com","cabecadegelo","https://i.imgur.com/FETvs2O.jpg"));
		usuarioRepository.save(new Usuario(0L, "Jose","jose@imperiobronze.com","trabalholindo","https://i.imgur.com/FETvs2O.jpg"));
	}
	
	@Test
	@DisplayName("Retorna apenas um usuário")
	public void deveRetornarUmUsuario() 
	{
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("cleitinho@pedra.com");
		assertTrue(usuario.get().getUsuario().equals("cleitinho@pedra.com"));
	}
	//Para buscar todos que possuem algo em comum no nome
	@Test
	@DisplayName("Retorna dois usuários.")
	public void deveRetornarDoisUsuario() 
	{
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("DJ"); //Para buscar todos que possuem algo em comum no nome "DJ", por exemplo.
		assertEquals(2, listaDeUsuarios.size()); // Para verificar se a quantidade pedidada (que é 2) atende a requisição da linha acima.
		
		assertTrue(listaDeUsuarios.get(0).getNome().equals("DJ Cleiton Rasta"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("DJ Laurinha Lero"));
	}
}
