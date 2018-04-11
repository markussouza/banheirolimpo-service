package com.inova.banheirolimpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inova.banheirolimpo.model.Cliente;
import com.inova.banheirolimpo.model.Funcao;
import com.inova.banheirolimpo.model.Funcionario;

/**
 * @author Markus Souza on 13/12/2017
 *
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	List<Funcionario> findAllByCliente(Cliente cliente);
	
	Funcionario findByNome(String nome);
	
	Funcionario findByPrimeiroNomeAndUltimoNome(String primeiroNome, String ultimoNome);
	
	Funcionario findByClienteAndFuncao(Cliente cliente, Funcao funcao);

}
