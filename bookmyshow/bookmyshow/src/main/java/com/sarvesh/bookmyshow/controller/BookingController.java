package com.sarvesh.bookmyshow.controller;

import com.sarvesh.bookmyshow.dto.BookingRequestDto;
import com.sarvesh.bookmyshow.dto.BookingResponseDto;
import com.sarvesh.bookmyshow.dto.ResponseStatus;
import com.sarvesh.bookmyshow.model.Booking;
import com.sarvesh.bookmyshow.service.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
        private BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }
    public BookingResponseDto createBooking(BookingRequestDto requestDto){
        BookingResponseDto responseDto = new BookingResponseDto();
        try {
            Booking booking = bookingService.createBooking(requestDto.getUserId(),
                    requestDto.getShowSeatIds(), requestDto.getShowId());

            responseDto.setBookingId(booking.getId());
            responseDto.setStatus(ResponseStatus.SUCCESS);

        }catch (Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
        }
}
