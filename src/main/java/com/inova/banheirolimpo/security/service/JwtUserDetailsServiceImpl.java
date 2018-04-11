/**
 * 
 */
package com.inova.banheirolimpo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inova.banheirolimpo.model.Usuario;
import com.inova.banheirolimpo.security.jwt.JwtUserFactory;
import com.inova.banheirolimpo.service.UsuarioService;


/**
 * @author Markus Souza on 19/03/2018
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findByNomeUsuario(nomeUsuario);
		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", nomeUsuario));
		}
		return JwtUserFactory.create(usuario);
	}

}
