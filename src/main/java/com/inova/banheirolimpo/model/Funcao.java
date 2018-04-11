/**
 * 
 */
package com.inova.banheirolimpo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.inova.banheirolimpo.enums.Situacao;

import lombok.Data;

/**
 * @author Markus Souza on 25/10/2017
 *
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"descricao"}, name = "UK_DESCRICAO_FUNCAO")})
@Data
public class Funcao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo descrição é de preenchimento obrigatório.")
	@Length(min = 5, max = 20, message = "O campo descrição deve conter entre 5 e 20 caracteres.")
	@Column(nullable = false, length = 20)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 7)
	private Situacao situacao;
	
}
