package com.sarvesh.bookmyshow.service;

import com.sarvesh.bookmyshow.exception.ShowNotFound;
import com.sarvesh.bookmyshow.exception.UserNotFoundException;
import com.sarvesh.bookmyshow.model.*;
import com.sarvesh.bookmyshow.repository.BookingRepository;
import com.sarvesh.bookmyshow.repository.ShowRepository;
import com.sarvesh.bookmyshow.repository.ShowSeatRepository;
import com.sarvesh.bookmyshow.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    private BookingRepository bookingRepository;
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository, ShowSeatRepository showSeatRepository
                          , PriceCalculatorService priceCalculatorService
                           , BookingRepository bookingRepository){
        this.userRepository=userRepository;
        this.showRepository=showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService=priceCalculatorService;
        this.bookingRepository=bookingRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws UserNotFoundException,ShowNotFound{
         /*
        1. Get the user with the given userId.
        2. Get the show with the given showId.
        3. Get the List of showSeats with the given id's.
        ----------TAKE A LOCK---------------
        4. Check if all the seats are available or not.
        5. If not, throw an exception.
        6. If yes, Mark the status of all the seats as BLOCKED.
        ----------RELEASE THE LOCK---------------
        7. Save the changes in the DB as well.
        8. Create the booking with pending status. [save booking obj to DB.]
        9. Return the booking object.
         */
        Optional<User> optionalUser  =userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("user with id"+userId+" not found");
        }
        User user = optionalUser.get();
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFound("show with id "+showId+" not found");
        }
        Show show = optionalShow.get();
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
//4. Check if all the seats are available or not.
//5. If not, throw an exception.
        for(ShowSeat showSeat:showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new RuntimeException("show seat with id "+showSeat.getId()+" isn't available");
            }
        }
//6. If yes, Mark the status of all the seats as BLOCKED.
        for(ShowSeat showSeat:showSeats){
           showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
           showSeatRepository.save(showSeat);
        }

        //8. Create the booking with pending status. [save booking obj to DB.]
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(new Date());
        booking.setUser(user);
        booking.setShow(show);
        booking.setPayments(new ArrayList<>());
        booking.setShowSeats(showSeats);
        booking.setAmount(priceCalculatorService.calculatePrice(show, showSeats));
        return bookingRepository.save(booking);





    }
}
