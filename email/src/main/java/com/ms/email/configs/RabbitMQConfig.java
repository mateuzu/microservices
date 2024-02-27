package com.ms.email.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

	@Value("${broker.queue.email.name}")
	private String queue;
	
	@Bean //Método produtor
	public Queue queue() {
		return new Queue(queue, false); //Cria uma nova fila no broker com o nome presente em application.properties
		//parâmetro queue: nome da fila
		//parâmetro false: indica se a fila é durável ou não, ou seja, indica se o broker cair e voltar essa fila será preservada ou não
	}
	
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		return new Jackson2JsonMessageConverter(objectMapper);
	}
}
