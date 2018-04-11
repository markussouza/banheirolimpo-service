package com.inova.banheirolimpo.resource;

import java.util.List;

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

import com.inova.banheirolimpo.model.Cliente;
import com.inova.banheirolimpo.repository.ClienteRepository;
import com.inova.banheirolimpo.service.ClienteService;

/**
 * 
 * @author Markus Souza
 * @since 17/10/2017
 *
 */

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping
	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterPorId(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findOne(id);
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
		try {
			Cliente novoCliente = clienteRepository.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		try {
			Cliente clienteAtualizado = clienteService.atualizar(id, cliente);
			return ResponseEntity.ok(clienteAtualizado);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		clienteRepository.delete(id);
	}
	
	
}
