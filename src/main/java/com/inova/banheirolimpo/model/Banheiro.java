package com.inova.banheirolimpo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * @author Markus Souza on 08/11/2017
 *
 */

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"}, name = "UK_NOME_BANHEIRO")})
@Data
public class Banheiro implements Serializable {
	
	private static final long serialVersionUID = -3505657055346485475L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome é de preenchimento obrigatório.")
	@Length(min = 10, max = 100, message = "O nome deve ter entre 10 e 100 caracteres.")
	@Column(nullable = false, length = 100)
	private String nome;
	
	@NotBlank(message = "O campo localização é de preenchimento obrigatório.")
	@Length(min = 5, max = 70, message = "O nome deve ter entre 5 e 70 caracteres.")
	@Column(nullable = false, length = 70)
	private String localizacao;
	
	@NotNull(message = "O campo quantodade de boxes é de preenchimento obrigatório.")
	@Column(name = "quantidade_boxes", nullable = false)
	private Integer quantidadeBoxes;
	
	@NotNull(message = "O campo limitador de limpeza é de preenchimento obrigatório.")
	@Column(name = "limitador_limpeza", nullable = false)
	private Integer limitadorLimpeza;
	
	private String codigoSensor;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_CLIENTE"))
	private Cliente cliente;

}
