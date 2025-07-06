package com.sarvesh.bookmyshow;

import com.sarvesh.bookmyshow.controller.UserController;
import com.sarvesh.bookmyshow.dto.LoginRequestDto;
import com.sarvesh.bookmyshow.dto.LoginResponseDto;
import com.sarvesh.bookmyshow.dto.SignupRequestDto;
import com.sarvesh.bookmyshow.dto.SignupResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookmyshowApplicationTests {
	@Autowired
	private UserController userController;
	@Test
	void contextLoads() {
	}


	@Test
	public void testSignUpFunctionality(){
		SignupRequestDto requestDto = new SignupRequestDto();
		requestDto.setName("ayush");
		requestDto.setEmail("ayush@gmail.com");
		requestDto.setPassword("bcde");
		SignupResponseDto responseDto = userController.signup(requestDto);
		System.out.println(responseDto.getUserId());
	}
@Test
	public void testLoginFunctionality(){
		LoginRequestDto requestDto = new LoginRequestDto();
		requestDto.setEmail("sarvesh@gmail.com");
		requestDto.setPassword("abcd");
		LoginResponseDto loginResponse = userController.login(requestDto);
		System.out.println(loginResponse.getStatus());
	}


}
