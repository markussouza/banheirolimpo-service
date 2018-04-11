/**
 * 
 */
package com.inova.banheirolimpo.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inova.banheirolimpo.model.EscalaTrabalho;
import com.inova.banheirolimpo.repository.EscalaTrabalhoRepository;

/**
 * @author Markus Souza on 13/03/2018
 *
 */
@RestController
@RequestMapping("/api/escalastrabalho")
@CrossOrigin(origins = "*")
public class EscalaTrabalhoResource {
	
	@Autowired
	private EscalaTrabalhoRepository escalaTrabalhoRepository;
	
	@GetMapping
	public ResponseEntity<List<EscalaTrabalho>> obterTodos() {
		return ResponseEntity.ok().body(escalaTrabalhoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EscalaTrabalho> obterPorId(@PathVariable Long id) {
		EscalaTrabalho escalaTrabalho = escalaTrabalhoRepository.findOne(id);
		return escalaTrabalho != null ? ResponseEntity.ok(escalaTrabalho) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<EscalaTrabalho> criar(@Valid @RequestBody EscalaTrabalho escalaTrabalho) {
		EscalaTrabalho novaEscalaTrabalho = escalaTrabalhoRepository.save(escalaTrabalho);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaEscalaTrabalho);
	}

}
