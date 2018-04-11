/**
 * 
 */
package com.inova.banheirolimpo.telegram.message.model;

import com.inova.banheirolimpo.telegram.model.Chat;
import com.inova.banheirolimpo.telegram.model.From;

import lombok.Data;

/**
 * @author Markus Souza on 19/12/2017
 *
 */
@Data
public class Result {

	private int message_id;
	private From from;
	private Chat chat;
	private int date;
	private String text;

}
