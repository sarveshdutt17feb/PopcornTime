package com.sarvesh.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {
    private Long userId;
    private ResponseStatus status;
}
