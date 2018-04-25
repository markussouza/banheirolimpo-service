/**
 * 
 */
package com.inova.banheirolimpo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inova.banheirolimpo.response.Response;
import com.inova.banheirolimpo.telegram.message.model.Message;
import com.inova.banheirolimpo.telegram.message.service.SendMessage;

/**
 * @author Markus Souza on 19/12/2017
 *
 */
@RestController
@RequestMapping("/api/notificacoes")
@CrossOrigin(origins = "*")
public class NotificacaoResource {
	
	@Autowired
	private SendMessage sendMenssage;
	
	@PostMapping("/{numeroSensor}")
	public ResponseEntity<Response<Message>> enviar(@PathVariable String numeroSensor) {
		Response<Message> response = new Response<>();
		try {
			Message message = sendMenssage.enviarMensagem(numeroSensor);
			response.setData(message);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

}
