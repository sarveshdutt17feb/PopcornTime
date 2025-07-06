package com.sarvesh.bookmyshow.service;

import com.sarvesh.bookmyshow.exception.UserAlreadyExistException;
import com.sarvesh.bookmyshow.exception.UserNotFoundException;
import com.sarvesh.bookmyshow.model.User;
import com.sarvesh.bookmyshow.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User signup(String name,String email, String password) throws UserAlreadyExistException {
        Optional<User> optionalUser  = userRepository.findByEmail(email);
        if(!optionalUser.isEmpty()){
            throw new UserAlreadyExistException("User with "+email+" already exist");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User login(String email,String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("user with email"+email+" not exist");
        }
        User user = optionalUser.get();
        if (passwordEncoder.matches(password,user.getPassword())) {
            //login successful
            return user;
        }
        throw new RuntimeException("password mismatch");

    }
}
