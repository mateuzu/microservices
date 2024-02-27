package com.ms.email.dto;

import java.util.UUID;

public record EmailRecordDto(UUID userId, //Id do usuário
							String emailTo, //Email do usuário cadastrado
							String subject, //Título do email
							String text) { //Texto email 

}
