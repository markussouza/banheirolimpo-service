/**
 * 
 */
package com.inova.banheirolimpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inova.banheirolimpo.model.Usuario;

/**
 * @author Markus Souza 10/04/2018
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNomeUsuario(String nomeUsuario);

}
