/**
 * 
 */
package com.inova.banheirolimpo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inova.banheirolimpo.model.Empresa;
import com.inova.banheirolimpo.repository.EmpresaRepository;

/**
 * @author Markus Souza on 01/02/2018
 *
 */
@Service
public class EmpresaService {
	
	private EmpresaRepository empresaRepository;
	
	public Empresa atualizar(Long id, Empresa empresa) {
		Empresa empresaSalva = empresaRepository.findOne(id);
		if (empresaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(empresa, empresaSalva, "id");
		return empresaRepository.save(empresaSalva);
	}

}
