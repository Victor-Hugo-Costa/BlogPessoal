package org.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByUsuario(String usuario); //Para mostrar (um ou lista) ou não o usuário, caso ele exista ou não exista.
	
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
}
