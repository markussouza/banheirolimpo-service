/**
 * 
 */
package com.inova.banheirolimpo.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.inova.banheirolimpo.enums.ProfileEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Markus Souza 10/04/2018
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome do usuário  requirido")
	private String nomeUsuario;
	
	@NotBlank(message = "Senha requirida")
	@Size(min = 6)
	private String senha;
	
	@Enumerated
	private ProfileEnum perfil;

}
