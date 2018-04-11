package com.inova.banheirolimpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

import com.inova.banheirolimpo.enums.Situacao;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Markus Souza on 08/11/2017
 *
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Cliente extends AbstractEmpresa {
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O campo centro de custo é de preenchimento obrigatório.")
	@Column(name = "centro_de_custo")
	private String centroDeCusto;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 7)
	private Situacao situacao;
	
	@NotBlank
	@Column(name = "nome_wifi", length = 70)
	private String nomeWifi;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id", foreignKey = @ForeignKey(name = "FK_EMPRESA"))
	private Empresa empresa;
	
}
