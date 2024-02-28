package com.ms.user.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ms.user.model.UserModel;
import com.ms.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Captor
	private ArgumentCaptor<UserModel> userArgumentCaptor;
	
	@Nested
	class saveUser{
		
		@Test
		@DisplayName("Deve criar um usuário com sucesso")
		void deveCriarUmUsuario() {
			
			//Arrange
			var user = new UserModel(UUID.randomUUID(), "name", "email@email.com");
			doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
			
			//Act
			var userReturn = userRepository.save(user);
			
			//Assert
			assertNotNull(userReturn);
		}
	}

}