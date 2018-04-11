/**
 * 
 */
package com.inova.banheirolimpo.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Markus Souza on 19/12/2017
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chat {

	private Long id;
	private String first_name;
	private String last_name;
	private String type;

}
