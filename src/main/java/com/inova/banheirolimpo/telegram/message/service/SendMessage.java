/**
 * 
 */
package com.inova.banheirolimpo.telegram.message.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inova.banheirolimpo.model.Banheiro;
import com.inova.banheirolimpo.model.Funcao;
import com.inova.banheirolimpo.model.Funcionario;
import com.inova.banheirolimpo.repository.FuncaoRepository;
import com.inova.banheirolimpo.repository.FuncionarioRepository;
import com.inova.banheirolimpo.service.BanheiroService;
import com.inova.banheirolimpo.telegram.message.model.Message;

/**
 * @author Markus Souza on 19/12/2017
 *
 */
@Service
public class SendMessage {
	
	private static final Logger log = LoggerFactory.getLogger(SendMessage.class);
	
	@Autowired
	private BanheiroService banheiroService;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${bot.token}")
	private String token;
	
	@Value("${bot.uri}")
	private String uri;
	
	
	public Message enviarMensagem(String codigoSensor) {
		
		String datetime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).toString();
		String hora = datetime.substring(11, 16);
		
		Message message = null;
		Banheiro banheiro = banheiroService.findByCodigoSensor(codigoSensor);
		
		if (banheiro != null) {
			String msg = String.format("Limite para limpeza do banheiro %s atingido às %s", banheiro.getNome(), hora);
			Funcao funcao = funcaoRepository.findByDescricaoIgnoreCase("ENCARREGADO");
			Funcionario funcionario = funcionarioRepository.findByClienteAndFuncao(banheiro.getCliente(), funcao);
			if (funcionario != null) {
				String endpoint = String.format("%sbot%s/sendMessage?chat_id=%s&text=%s", uri, token, funcionario.getTelegramChatId(), msg);
				message = restTemplate.postForObject(endpoint, null, Message.class);
			}
		}
		log.info(message.toString());
		
		return message;
	}

}
