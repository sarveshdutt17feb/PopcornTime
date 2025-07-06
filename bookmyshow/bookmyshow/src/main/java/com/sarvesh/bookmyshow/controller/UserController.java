package com.sarvesh.bookmyshow.controller;

import com.sarvesh.bookmyshow.dto.*;
import com.sarvesh.bookmyshow.exception.UserAlreadyExistException;
import com.sarvesh.bookmyshow.exception.UserNotFoundException;
import com.sarvesh.bookmyshow.model.User;
import com.sarvesh.bookmyshow.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    public SignupResponseDto signup(SignupRequestDto requestDto){
        SignupResponseDto responseDto = new SignupResponseDto();
        try {


            User user = userService.signup(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
            responseDto.setUserId(user.getId());
            responseDto.setStatus(ResponseStatus.SUCCESS);

        }catch (UserAlreadyExistException e){
            responseDto.setStatus(ResponseStatus.FAILURE);
            System.out.println(e.getMessage());
        }
        return responseDto;
        }

    public LoginResponseDto login(LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try {
            User login = userService.login(requestDto.getEmail(), requestDto.getPassword());
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return responseDto;
    }
}
