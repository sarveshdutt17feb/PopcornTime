package com.sarvesh.bookmyshow.dto;

import lombok.Data;
import lombok.Getter;

import lombok.Setter;

import java.util.List;

@Data
public class BookingRequestDto {

    private Long userId;
    private Long showId; // Can be skipped as we have showSeatId's
    private List<Long> showSeatIds;
    public Long getUserId() {
        return userId;
    }
}
