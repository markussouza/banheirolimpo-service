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
public class Entity {

	private int offset;
	private int length;
	private String type;

}
