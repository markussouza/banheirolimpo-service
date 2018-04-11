/**
 * 
 */
package com.inova.banheirolimpo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import com.inova.banheirolimpo.enums.Estados;

import lombok.Data;

/**
 * @author Markus Souza on 08/11/2017
 *
 */
@MappedSuperclass
@Data
public abstract class AbstractEmpresa implements Serializable {

	private static final long serialVersionUID = -8334204095835331718L;
	
	@Id
	@Column(insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo razão social é de preenchimento obrigatório.")
	@Column(name = "razao_social", length = 200)
	private String razaoSocial;
	
	@NotBlank(message = "O campo nome é de preenchimento obrigatório.")
	@Column(name = "nome_fantasia", length = 200)
	private String nomeFantasia;
	
	@NotBlank(message = "O campo CNPJ é de preenchimento obrigatório.")
	@Column(length = 18)
	@CNPJ(message = "CNPJ inválido.")
	private String cnpj;
	
	@NotBlank(message = "O campo endereco é de preenchimento obrigatório.")
	@Column(length = 100)
	private String endereco;
	
	@NotBlank(message = "O campo bairro é de preenchimento obrigatório.")
	@Column(length = 150)
	private String bairro;
	
	@NotBlank(message = "O campo cidade é de preenchimento obrigatório.")
	@Column(length = 70)
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 2, nullable = false)
	private Estados uf;
	
	@Column(length = 9, nullable = false)
	@Pattern(regexp = "(\\d{5})-(\\d{3})")
	private String cep;
	
}
