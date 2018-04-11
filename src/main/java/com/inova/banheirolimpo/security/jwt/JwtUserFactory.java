/**
 * 
 */
package com.inova.banheirolimpo.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.inova.banheirolimpo.enums.ProfileEnum;
import com.inova.banheirolimpo.model.Usuario;


/**
 * @author Markus Souza on 19/03/2018
 *
 */
public class JwtUserFactory {
	
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getNomeUsuario(), usuario.getSenha(), mapToGrantedAuthorities(usuario.getPerfil()));
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(profileEnum.toString()));
		return authorities;
	}

}
