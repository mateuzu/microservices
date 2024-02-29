package com.ms.email.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.email.enums.StatusEmail;
import com.ms.email.model.EmailModel;
import com.ms.email.repository.EmailRepository;

@Service
public class EmailService {

	private EmailRepository emailRepository;
	private JavaMailSender emailSender; //Realiza o envio de email

	@Autowired
	public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
		this.emailRepository = emailRepository;
		this.emailSender = emailSender;
	}
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	@Transactional
	public EmailModel sendEmail(EmailModel emailModel) {
		try {
			emailModel.setSendDateEmail(LocalDateTime.now());
			emailModel.setEmailFrom(emailFrom);
			
			SimpleMailMessage message = new SimpleMailMessage(); //Cria a mensagem de email e preenche com os dados do objeto
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message); //Envia a mensagem
			
			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch(MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		} finally {
			return emailRepository.save(emailModel);
		}
	}
	
	
}
