package com.accenture.SmartOffice.controller;

import com.accenture.SmartOffice.model.web.WebBookingModel;
import com.accenture.SmartOffice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/room")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    private static final String BOOKING = "/book";
    private static final String DELETE_BOOKING = "/deleteBooking";
    private static final String EDIT_BOOKING = "/editBooking";
    private static final String BOOKING_PLAN = "/getBookingPlan";

    @ResponseBody
    @PostMapping(value = BOOKING)
    public WebBookingModel bookRoom(@RequestBody WebBookingModel webBookingModel) {
        //TODO
//        return bookingService.bookRoom(webBookingModel);
        throw new UnsupportedOperationException();
    }

    @ResponseBody
    @PostMapping(value = DELETE_BOOKING)
    public WebBookingModel deleteBooking(@PathVariable Long bookingId) {
        //TODO
//        return bookingService.deleteBooking(bookingId);
        throw new UnsupportedOperationException();
    }

    @ResponseBody
    @PostMapping(value = EDIT_BOOKING)
    public WebBookingModel editBooking(@RequestBody WebBookingModel webBookingModel) {
        //TODO
//        return bookingService.editBooking(webBookingModel);
        throw new UnsupportedOperationException();
    }

    @ResponseBody
    @GetMapping(value = BOOKING_PLAN)
    public WebBookingModel getBookingPlan(@PathVariable Long roomId) {
        //TODO
//        return bookingService.getBookingPlan(roomId);
        throw new UnsupportedOperationException();
    }
}
