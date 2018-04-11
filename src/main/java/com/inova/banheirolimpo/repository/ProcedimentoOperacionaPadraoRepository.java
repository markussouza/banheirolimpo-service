/**
 * 
 */
package com.inova.banheirolimpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inova.banheirolimpo.model.ProcedimentoOperacionalPadrao;

/**
 * @author Markus Souza
 * @since 06/11/2017
 *
 */

@Repository
public interface ProcedimentoOperacionaPadraoRepository extends JpaRepository<ProcedimentoOperacionalPadrao, Long> {

}
