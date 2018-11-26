package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.dao.entity.Meeting;
import com.accenture.SmartOffice.dao.entity.MeetingUser;
import com.accenture.SmartOffice.dao.entity.User;
import com.accenture.SmartOffice.dao.repository.MeetingRepository;
import com.accenture.SmartOffice.dao.repository.UserRepository;
import com.accenture.SmartOffice.model.web.WebBookingModel;
import com.accenture.SmartOffice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public WebBookingModel bookRoom(WebBookingModel webBookingModel){
        if (webBookingModel.getStartTime().isAfter(webBookingModel.getEndTime())) {
            throw new IllegalArgumentException("Некорректно задан временной промежуток");
        }
        Meeting meeting = meetingRepository.findByMeetingByIdAndTimeLines(webBookingModel.getRoomId(), webBookingModel.getStartTime(), webBookingModel.getEndTime());
        if (meeting != null) {
            throw new IllegalArgumentException("На данный промежуток времени переговорка уже забронирована");
        }
        meeting = new Meeting();
        meeting.setName(webBookingModel.getName());
        meeting.setDescription(webBookingModel.getDescription());
        meeting.setStartTime(webBookingModel.getStartTime());
        meeting.setEndTime(webBookingModel.getEndTime());
        User user =  userRepository.getOne(webBookingModel.getOwnerId());
        if (user == null) {
            throw new IllegalArgumentException(String.format("Пользователь с данным id=%d не найден", webBookingModel.getOwnerId()));
        }
        meeting.setOwner(user);
        final Meeting createdMeeting = meetingRepository.save(meeting);
        if (webBookingModel.getUserIds() != null && !webBookingModel.getUserIds().isEmpty()) {
            webBookingModel.getUserIds().forEach( id -> {
                User usr = userRepository.getOne(id);
                if (usr == null) {
                    throw new IllegalArgumentException(String.format("Пользователь с данным id=%d не найден", id));
                }
                MeetingUser meetingUser = new MeetingUser();
                meetingUser.setUser(usr);
                meetingUser.setMeeting(createdMeeting);
            });
        }
        return new WebBookingModel();
    }

    @Override
    public void deleteBooking(Long bookingId){
        meetingRepository.delete(bookingId);
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
