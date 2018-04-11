package com.inova.banheirolimpo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inova.banheirolimpo.model.Banheiro;
import com.inova.banheirolimpo.repository.BanheiroRepository;

@Service
public class BanheiroService {
	
	@Autowired
	private BanheiroRepository banheiroRepository;
	
	public Banheiro atualizar(Long id, Banheiro banheiro) {
		Banheiro banheiroSalvo = banheiroRepository.findOne(id);
		if (banheiroSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(banheiro, banheiroSalvo, "id");
		return banheiroRepository.save(banheiroSalvo);
	}
	
	public Banheiro findByCodigoSensor(String codigoSensor) {
		return banheiroRepository.findByCodigoSensor(codigoSensor);
	}

}
