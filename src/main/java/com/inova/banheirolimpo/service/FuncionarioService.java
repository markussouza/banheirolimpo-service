package com.inova.banheirolimpo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inova.banheirolimpo.model.Funcionario;
import com.inova.banheirolimpo.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario atualizar(Long id, Funcionario funcionario) {
		Funcionario funcionarioSalvo = funcionarioRepository.findOne(id);
		if (funcionarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(funcionario, funcionarioSalvo, "id");
		return funcionarioRepository.save(funcionarioSalvo);
	}

}
