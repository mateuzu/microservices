package com.ms.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.dto.EmailRecordDto;
import com.ms.user.model.UserModel;

@Component
public class UserProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	//Routing key = chave para rotear a mensagem ao exchange
	@Value("${broker.queue.email.name}")
	private String routingKey;
	
	//Método publishMessageEmail = Envia a mensagem convertida
	public void publishMessageEmail(UserModel userModel) {
		var emailDto = new EmailRecordDto();
		emailDto.setUserId(userModel.getUserId());
		emailDto.setEmailTo(userModel.getEmail());
		emailDto.setSubject("Cadastro realizado com sucesso!");
		emailDto.setText("Olá " + userModel.getName() + ", seja bem vindo(a)!" + "\n\nAgradecemos o seu cadastro em nosso sistema! pai apredeu microserviços cumpade");
		
		//3 parametros = Exchange, routingKey e a mensagem
		rabbitTemplate.convertAndSend("", routingKey, emailDto);
	}
}
