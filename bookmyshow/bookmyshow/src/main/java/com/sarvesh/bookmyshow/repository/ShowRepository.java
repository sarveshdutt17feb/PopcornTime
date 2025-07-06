package com.sarvesh.bookmyshow.repository;

import com.sarvesh.bookmyshow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Long> {
    @Override
    Optional<Show> findById(Long showId);
}
