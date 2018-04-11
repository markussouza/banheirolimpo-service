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

import com.inova.banheirolimpo.model.Funcionario;
import com.inova.banheirolimpo.repository.FuncionarioRepository;
import com.inova.banheirolimpo.service.FuncionarioService;

/**
 * 
 * @author Markus Souza
 * @since 17/10/2017
 *
 */

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> obterPorId(@PathVariable Long id) {
		Funcionario funcionario = funcionarioRepository.findOne(id);
		return funcionario != null ? ResponseEntity.ok(funcionario) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Funcionario> criar(@Valid @RequestBody Funcionario funcionario, HttpServletResponse response) {
		Funcionario novoFuncionario = funcionarioRepository.save(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
		Funcionario funcionarioAtualizado = funcionarioService.atualizar(id, funcionario);
		return ResponseEntity.ok(funcionarioAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		funcionarioRepository.delete(id);
	}

}
