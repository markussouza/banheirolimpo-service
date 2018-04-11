/**
 * 
 */
package com.inova.banheirolimpo.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inova.banheirolimpo.model.Usuario;
import com.inova.banheirolimpo.security.jwt.JwtAuthenticationRequest;
import com.inova.banheirolimpo.security.jwt.JwtTokenUtil;
import com.inova.banheirolimpo.security.model.CurrentUser;
import com.inova.banheirolimpo.service.UsuarioService;


/**
 * @author Markus Souza on 20/03/2018
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class AutenticacaoResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping("/api/auth")
	public ResponseEntity<?> createAthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getNomeUsuario(),
						authenticationRequest.getSenha()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getNomeUsuario());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final Usuario usuario = usuarioService.findByNomeUsuario(authenticationRequest.getNomeUsuario());
		usuario.setSenha(null);
		return ResponseEntity.ok(new CurrentUser(token, usuario));
	}
	
	@PostMapping("/api/refresh")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String nomeUsuario = jwtTokenUtil.getUsernameFromToken(token);
		final Usuario usuario = usuarioService.findByNomeUsuario(nomeUsuario);
		
		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			return ResponseEntity.ok(new CurrentUser(token, usuario));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}

}
