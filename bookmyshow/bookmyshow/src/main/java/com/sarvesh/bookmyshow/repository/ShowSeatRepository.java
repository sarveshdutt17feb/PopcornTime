package com.sarvesh.bookmyshow.repository;

import com.sarvesh.bookmyshow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);
}
