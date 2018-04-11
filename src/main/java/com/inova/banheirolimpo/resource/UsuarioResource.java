/**
 * 
 */
package com.inova.banheirolimpo.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inova.banheirolimpo.model.Usuario;
import com.inova.banheirolimpo.response.Response;
import com.inova.banheirolimpo.service.UsuarioService;


/**
 * @author Markus Souza 10/04/2018
 *
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Response<Page<Usuario>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<Usuario>> response = new Response<>();
		Page<Usuario> usuarios = usuarioService.findAll(page, count);
		response.setData(usuarios);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Response<Usuario>> findById(@PathVariable Long id) {
		Response<Usuario> response = new Response<>();
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(usuario);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Response<Usuario>> create(HttpServletRequest request, @RequestBody Usuario usuario, BindingResult result) {
		Response<Usuario> response = new Response<>();
		try {
			validateCreateUser(usuario, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			response.setData(usuarioService.salvar(usuario));
		} catch (DuplicateKeyException e) {
			response.getErrors().add("Email already registered!");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Response<Usuario>> update(HttpServletRequest request, @RequestBody Usuario usuario, BindingResult result) {
		Response<Usuario> response = new Response<>();
		try {
			validateUpdateUser(usuario, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			Usuario usuarioPersistido = usuarioService.salvar(usuario);
			response.setData(usuarioPersistido);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
		Response<String> response = new Response<>();
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		usuarioService.excluir(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	private void validateCreateUser(Usuario user, BindingResult result) {
		if (user.getNomeUsuario() == null) {
			result.addError(new ObjectError("Usuário", "Nome do usuário não informado"));
		}
	}
	
	private void validateUpdateUser(Usuario user, BindingResult result) {
		if (user.getId() == null) {
			result.addError(new ObjectError("Usuário", "Id não informado"));
		}
	}

}
