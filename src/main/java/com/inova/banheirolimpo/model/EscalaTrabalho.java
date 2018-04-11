/**
 * 
 */
package com.inova.banheirolimpo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.inova.banheirolimpo.enums.Situacao;

import lombok.Data;

/**
 * @author Markus Souza on 08/11/2017
 *
 */

@Entity
@Table(name = "escala_trabalho")
@Data
public class EscalaTrabalho implements Serializable {
	
	private static final long serialVersionUID = 3741769976893602961L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "A descrição é de preenchimento obrigatório.")
	@Size(min = 3, max = 15, message = "A descrição deve conter no mínimo {min} caracteres.")
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

}
