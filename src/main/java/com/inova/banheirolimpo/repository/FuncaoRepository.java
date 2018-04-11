package com.inova.banheirolimpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inova.banheirolimpo.model.Funcao;

/**
 * @author Markus Souza on 13/12/2017
 *
 */

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
	
	Funcao findByDescricaoIgnoreCase(String descricao);

}
