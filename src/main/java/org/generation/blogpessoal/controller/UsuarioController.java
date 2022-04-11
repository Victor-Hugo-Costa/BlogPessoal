package org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.model.UsuarioLogin;
import org.generation.blogpessoal.repository.UsuarioRepository;
import org.generation.blogpessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll()
    {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody @Valid Usuario usuario)
    {
        return usuarioService.cadastrarUsuario(usuario)
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> logaUsuario(@RequestBody @Valid Optional<UsuarioLogin> usuario) 
    {
        return usuarioService.autenticarUsuario(usuario)
        		.map(resposta -> ResponseEntity.ok(resposta))
        		.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    
    @PutMapping("/atualizar") 
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario)
    {		
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
    
    @DeleteMapping("/{id}") //{Id da variável a ser deletada}
	public void delete(@PathVariable Long id) 
	{
		usuarioRepository.deleteById(id);
	}
}