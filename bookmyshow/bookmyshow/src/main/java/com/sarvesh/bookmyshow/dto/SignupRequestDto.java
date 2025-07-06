package com.sarvesh.bookmyshow.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignupRequestDto {
    private String name;
    private String email;
    private String password;
}
