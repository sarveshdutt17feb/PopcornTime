package com.sarvesh.bookmyshow.repository;

import com.sarvesh.bookmyshow.model.Show;
import com.sarvesh.bookmyshow.model.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {
   List<ShowSeatType> findAllByShow(Show show);
}
