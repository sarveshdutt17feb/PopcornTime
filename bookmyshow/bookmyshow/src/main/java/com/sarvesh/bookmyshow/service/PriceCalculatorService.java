package com.sarvesh.bookmyshow.service;

import com.sarvesh.bookmyshow.model.Show;
import com.sarvesh.bookmyshow.model.ShowSeat;
import com.sarvesh.bookmyshow.model.ShowSeatType;
import com.sarvesh.bookmyshow.repository.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  PriceCalculatorService{
    private ShowSeatTypeRepository showSeatTypeRepository;
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository=showSeatTypeRepository;
    }
    public int calculatePrice(Show show, List<ShowSeat> showSeats){
    int amount = 0;
        List<ShowSeatType> ShowSeatTypes = showSeatTypeRepository.findAllByShow(show);
        for(ShowSeat showSeat:showSeats){
            for(ShowSeatType showSeatType:ShowSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount+=showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
