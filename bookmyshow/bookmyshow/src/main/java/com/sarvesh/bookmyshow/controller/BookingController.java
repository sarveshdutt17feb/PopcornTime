package com.sarvesh.bookmyshow.controller;

import com.sarvesh.bookmyshow.dto.BookingRequestDto;
import com.sarvesh.bookmyshow.dto.BookingResponseDto;
import com.sarvesh.bookmyshow.dto.ResponseStatus;
import com.sarvesh.bookmyshow.model.Booking;
import com.sarvesh.bookmyshow.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bms/v1/bookings")
public class BookingController {
    private BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }
    @PostMapping()
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto requestDto){
        BookingResponseDto responseDto = new BookingResponseDto();
        try {
            Booking booking = bookingService.createBooking(requestDto.getUserId(),
                    requestDto.getShowSeatIds(), requestDto.getShowId());

            responseDto.setBookingId(booking.getId());
            responseDto.setStatus(ResponseStatus.SUCCESS);

        }catch (Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }
}
