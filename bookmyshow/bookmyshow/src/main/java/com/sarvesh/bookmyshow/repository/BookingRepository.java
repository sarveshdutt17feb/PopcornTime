package com.sarvesh.bookmyshow.repository;

import com.sarvesh.bookmyshow.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Override
    Booking save(Booking booking);
}
