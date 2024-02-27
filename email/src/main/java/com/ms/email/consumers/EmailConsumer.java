package com.ms.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dto.EmailRecordDto;

@Component
public class EmailConsumer {

	@RabbitListener(queues = "${broker.queue.email.name}") //Indica que o método será um ouvinte e como parâmetro qual fila ele vai ouvir
	public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) { //@Paylod: Recebe o corpo da mensagem enviada na comunicação assíncrona
		System.out.println(emailRecordDto.emailTo());
	}
}
