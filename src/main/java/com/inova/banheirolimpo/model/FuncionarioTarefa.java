package com.inova.banheirolimpo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Markus Souza on 08/11/2017
 *
 */
@Entity
@Table(name = "Funcionario_Tarefa")
@Data
public class FuncionarioTarefa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "funcionario_id", foreignKey = @ForeignKey(name = "FK_FUNCIONARIO"))
	private Funcionario funcionario;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "tarefa_id", foreignKey = @ForeignKey(name = "FK_TAREFA"))
	private Tarefa tarefa;
	
	@Column(name = "inicio_tarefa")
	private LocalDate inicioTarefa;
	
	@Column(name = "termino_tarefa")
	private LocalDate terminoTarefa;

}
