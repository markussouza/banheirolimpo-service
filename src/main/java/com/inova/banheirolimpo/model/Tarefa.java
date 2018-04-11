package com.inova.banheirolimpo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * @author Markus Souza on 08/11/2017
 *
 */
@Entity
@Data
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo nome é de preenchimento obrigatório.")
	@Column(nullable = false, length = 70)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarefa")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<FuncionarioTarefa> funcionarioTarefas = new HashSet<>();
	
}
