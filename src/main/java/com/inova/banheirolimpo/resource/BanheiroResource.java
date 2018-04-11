package com.inova.banheirolimpo.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inova.banheirolimpo.model.Banheiro;
import com.inova.banheirolimpo.repository.BanheiroRepository;
import com.inova.banheirolimpo.service.BanheiroService;

/**
 * 
 * @author Markus Souza
 * @since 17/10/2017
 *
 */

@RestController
@RequestMapping("/api/banheiros")
@CrossOrigin(origins = "*")
public class BanheiroResource {
	
	@Autowired
	private BanheiroRepository banheiroRepository;
	
	@Autowired
	private BanheiroService banheiroService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Banheiro> obterPorId(@PathVariable Long id) {
		Banheiro banheiro = banheiroRepository.findOne(id);
		return banheiro != null ? ResponseEntity.ok(banheiro) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Banheiro> criar(@Valid @RequestBody Banheiro banheiro, HttpServletResponse response) {
		Banheiro novoBanheiro = banheiroRepository.save(banheiro);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoBanheiro);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Banheiro> atualizar(@PathVariable Long id, @Valid @RequestBody Banheiro banheiro) {
		Banheiro banheiroAtualizado = banheiroService.atualizar(id, banheiro);
		return ResponseEntity.ok(banheiroAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Banheiro banheiro = banheiroRepository.findOne(id);
		banheiroRepository.delete(banheiro);
	}

}
