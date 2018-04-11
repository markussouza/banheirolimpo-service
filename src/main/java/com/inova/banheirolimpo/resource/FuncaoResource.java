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

import com.inova.banheirolimpo.model.Funcao;
import com.inova.banheirolimpo.repository.FuncaoRepository;

/**
 * @author Markus Souza on 13/03/2018
 *
 */
@RestController
@RequestMapping("/api/funcoes")
@CrossOrigin(origins = "*")
public class FuncaoResource {
	
	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@GetMapping
	public ResponseEntity<List<Funcao>> obterTodos() {
		return ResponseEntity.ok().body(funcaoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcao> obterPorId(@PathVariable Long id) {
		Funcao funcao = funcaoRepository.findOne(id);
		return funcao != null ? ResponseEntity.ok(funcao) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Funcao> criar(@Valid @RequestBody Funcao funcao) {
		Funcao novaFuncao = funcaoRepository.save(funcao);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaFuncao);
	}
}
