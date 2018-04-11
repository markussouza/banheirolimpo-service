package com.inova.banheirolimpo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import com.inova.banheirolimpo.enums.Situacao;

import lombok.Data;

/**
 * @author Markus Souza on 08/11/2017
 *
 */
@Entity
@Data
public class Funcionario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome é de preenchimento obrigatório.")
	@Column(nullable = false, length = 150)
	private String nome;
	
	@Column(name = "primeiro_nome", nullable = false, length = 50)
	private String primeiroNome;
	
	@Column(name = "ultimo_nome", nullable = false, length = 50)
	private String ultimoNome;
	
	@NotBlank(message = "O campo matrícula é de preenchimento obrigatório.")
	@Column(nullable = false, length = 9)
	private String matricula;
	
	@Column(name = "telegram_chat_id")
	private Long telegramChatId;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 7)
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name = "funcao_id", foreignKey = @ForeignKey(name = "FK_FUNCAO"))
	private Funcao funcao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_CLIENTE"))
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "escala_trabalho_id", foreignKey = @ForeignKey(name = "FK_ESCALA_TRABALHO"))
	private EscalaTrabalho escalaTrabalho;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario")
	@Cascade({CascadeType.SAVE_UPDATE})
	@OrderBy("tarefa asc")
	private Set<FuncionarioTarefa> funcionarioTarefas = new HashSet<>(0);
	
}
