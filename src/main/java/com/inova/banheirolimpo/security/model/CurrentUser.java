/**
 * 
 */
package com.inova.banheirolimpo.security.model;


import com.inova.banheirolimpo.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Markus Souza on 20/03/2018
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {
	
	private String token;
	private Usuario usuario;

}
