package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.model.web.WebBookingModel;

public interface BookingService {

    WebBookingModel bookRoom(WebBookingModel webBookingModel);

    void deleteBooking(Long bookingId);

    WebBookingModel editBooking(WebBookingModel webBookingModel);

    WebBookingModel getBookingPlan(Long roomId);
}
