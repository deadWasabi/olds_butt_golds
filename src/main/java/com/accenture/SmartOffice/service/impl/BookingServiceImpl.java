package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.model.web.WebBookingModel;
import com.accenture.SmartOffice.service.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Override
    public WebBookingModel bookRoom(WebBookingModel webBookingModel){
        //TODO
        return new WebBookingModel();
    }

    @Override
    public void deleteBooking(Long bookingId){
        //TODO
    }

    @Override
    public WebBookingModel editBooking(WebBookingModel webBookingModel){
        //TODO
        return new WebBookingModel();
    }

    @Override
    public WebBookingModel getBookingPlan(Long roomId){
        //TODO
        return new WebBookingModel();
    }
}
