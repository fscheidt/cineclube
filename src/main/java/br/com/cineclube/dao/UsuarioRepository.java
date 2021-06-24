package br.com.cineclube.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cineclube.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
}
