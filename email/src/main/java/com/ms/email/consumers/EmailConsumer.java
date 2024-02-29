package com.ms.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dto.EmailRecordDto;
import com.ms.email.model.EmailModel;
import com.ms.email.service.EmailService;

@Component
public class EmailConsumer {
	
	@Autowired
	private EmailService emailService;

	@RabbitListener(queues = "${broker.queue.email.name}") //Indica que o método será um ouvinte e como parâmetro qual fila ele vai ouvir
	public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) { //@Paylod: Recebe o corpo da mensagem enviada na comunicação assíncrona
		var emailModel = new EmailModel();
		
		//DTO -> MODEL
		BeanUtils.copyProperties(emailRecordDto, emailModel);
		
		System.out.println(emailModel.getEmailTo() + " TESTE " + emailModel.getEmailFrom());
		
		//sendEmail
		emailService.sendEmail(emailModel);
		
	}
}
