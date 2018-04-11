/**
 * 
 */
package com.inova.banheirolimpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.inova.banheirolimpo.model.Usuario;
import com.inova.banheirolimpo.repository.UsuarioRepository;

/**
 * @author Markus Souza 10/04/2018
 *
 */
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario findByNomeUsuario(String nomeUsuario) {
		return usuarioRepository.findByNomeUsuario(nomeUsuario);
	}
	
	public Usuario findById(Long id) {
		return usuarioRepository.findOne(id);
	}
	
	public Page<Usuario> findAll(int page, int count) {
		return usuarioRepository.findAll(new PageRequest(page, count));
	}
	
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void excluir(Long id) {
		usuarioRepository.delete(id);
	}

}
