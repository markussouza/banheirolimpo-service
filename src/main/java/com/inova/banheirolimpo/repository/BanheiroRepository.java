package com.inova.banheirolimpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inova.banheirolimpo.model.Banheiro;
import com.inova.banheirolimpo.model.Cliente;

@Repository
public interface BanheiroRepository extends JpaRepository<Banheiro, Long> {
	
	List<Banheiro> findAllByCliente(Cliente cliente);
	
	Banheiro findByCodigoSensor(String codigoSensor);

}
