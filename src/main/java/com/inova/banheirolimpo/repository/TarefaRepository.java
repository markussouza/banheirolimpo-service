/**
 * 
 */
package com.inova.banheirolimpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inova.banheirolimpo.model.Tarefa;

/**
 * @author markussouza
 *
 */

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
