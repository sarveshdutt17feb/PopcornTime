package com.sarvesh.bookmyshow.controller;

import com.sarvesh.bookmyshow.dto.*;
import com.sarvesh.bookmyshow.dto.ResponseStatus;
import com.sarvesh.bookmyshow.exception.UserAlreadyExistException;
import com.sarvesh.bookmyshow.exception.UserNotFoundException;
import com.sarvesh.bookmyshow.model.User;
import com.sarvesh.bookmyshow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bms/v1/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    public String checkEndPoint(){
        return "hello";
    }
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto requestDto){
        SignupResponseDto responseDto = new SignupResponseDto();
        try {


            User user = userService.signup(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
            responseDto.setUserId(user.getId());
            responseDto.setStatus(ResponseStatus.SUCCESS);

        }catch (UserAlreadyExistException e){
            responseDto.setStatus(ResponseStatus.FAILURE);
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try {
            User login = userService.login(requestDto.getEmail(), requestDto.getPassword());
            responseDto.setStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseDto.setStatus(ResponseStatus.FAILURE);

            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }

    }
}
