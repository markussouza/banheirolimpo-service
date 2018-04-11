/**
 * 
 */
package com.inova.banheirolimpo.telegram.update.model;

import com.inova.banheirolimpo.telegram.model.Message;

import lombok.Data;

/**
 * @author Markus Souza on 19/12/2017
 *
 */
@Data
public class Result {

	private int update_id;
	private Message message;

}
