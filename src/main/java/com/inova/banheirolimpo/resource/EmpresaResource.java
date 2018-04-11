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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inova.banheirolimpo.model.Empresa;
import com.inova.banheirolimpo.repository.EmpresaRepository;
import com.inova.banheirolimpo.service.EmpresaService;

/**
 * @author Markus Souza on 01/02/2018
 *
 */
@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaResource {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public ResponseEntity<List<Empresa>> obterTodos() {
		return ResponseEntity.ok().body(empresaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empresa> obterPorId(@PathVariable Long id) {
		Empresa empresa = empresaRepository.findOne(id);
		return empresa != null ? ResponseEntity.ok(empresa) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	public ResponseEntity<Empresa> criar(@Valid @RequestBody Empresa empresa) {
		//TenantContext.setCurrentTenant(tenantName);
		Empresa novaEmpresa = empresaRepository.save(empresa);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'GERENTE')")
	public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @Valid @RequestBody Empresa empresa) {
		Empresa empresaAtualizada = empresaService.atualizar(id, empresa);
		return ResponseEntity.ok(empresaAtualizada);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'GERENTE')")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		empresaRepository.delete(id);
		return ResponseEntity.ok().build();
	}

}
