/**
 * 
 */
package com.inova.banheirolimpo.telegram.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Markus Souza on 19/12/2017
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

	private int message_id;
	private From from;
	private Chat chat;
	private int date;
	private String text;
	private List<Entity> entities;

}
