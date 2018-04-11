/**
 * 
 */
package com.inova.banheirolimpo.telegram.update.model;

import java.util.List;

import lombok.Data;

/**
 * @author Markus Souza on 19/12/2017
 *
 */
@Data
public class Update {

	private boolean ok;
	private List<Result> result;

}
