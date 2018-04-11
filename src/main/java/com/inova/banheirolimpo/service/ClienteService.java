package com.inova.banheirolimpo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inova.banheirolimpo.model.Cliente;
import com.inova.banheirolimpo.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository estabelecimentoRepository;
	
	public Cliente atualizar(Long id, Cliente estabelecimento) {
		Cliente estabelecimentoSalvo = estabelecimentoRepository.findOne(id);
		if (estabelecimentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(estabelecimento, estabelecimentoSalvo, "id");
		return estabelecimentoRepository.save(estabelecimentoSalvo);
	}

}
