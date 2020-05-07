package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.SlotRequest;
import com.example.demo.model.SlotStatus;
import com.example.demo.repos.SlotRequestRepository;

@Service
public class SlotRequestService {

    @Autowired
    private SlotRequestRepository repository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EntityService entityService;

    @Autowired
    private UserService userService;

    public SlotRequest save(SlotRequest slotRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        slotRequest.setUserEmail(authentication.getName());
        slotRequest.setEntityName(entityService.findEntityById(slotRequest.getEntityId()).get().getName());
        slotRequest.setBookingDate(LocalDate.now().getMonth()+"-"+LocalDate.now().getDayOfMonth()+"-"+LocalDate.now().getYear());

        // check if capacity for that slot duration is available
        if (checkIfSlotIsAvailable(slotRequest.getEntityId(), slotRequest.getDateOfRequest(),
                slotRequest.getStartTime(), slotRequest.getEndTime())) {
            slotRequest.setStatus(SlotStatus.APPROVED);
            notificationService.addNotification("Slot Request approved",
                    userService.findUserByEmail(slotRequest.getUserEmail()).get().getId());
        } else {
            slotRequest.setStatus(SlotStatus.REJECTED);
            notificationService.addNotification("Slot Request rejected",
                    userService.findUserByEmail(slotRequest.getUserEmail()).get().getId());
        }
        SlotRequest sr = repository.save(slotRequest);
        return sr;
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

    public List<SlotRequest> getAll() {
        Iterable<SlotRequest> iterable = repository.findAll();
        List<SlotRequest> slotRequests = new ArrayList<>();
        iterable.forEach(new Consumer<SlotRequest>() {
            @Override
            public void accept(SlotRequest t) {
                slotRequests.add(t);
            }

        });
        return slotRequests;
    }

    public List<SlotRequest> getAll(String userId) {
        Iterable<SlotRequest> iterable = repository.findByUserEmail(userService.findUserById(userId).get().getEmail());
        List<SlotRequest> slotRequests = new ArrayList<>();
        iterable.forEach(new Consumer<SlotRequest>() {
            @Override
            public void accept(SlotRequest t) {
                slotRequests.add(t);
            }

        });
        return slotRequests;
    }

    public boolean checkIfSlotIsAvailable(String entityId, String dateOfRequest, long startTime, long endTime) {
    	System.out.println(startTime + ":" +endTime);
        int unavailableCapacityForTheSlot = repository
                .findByEntityIdAndDateOfRequestAndStartTimeAndEndTimeAndStatus(entityId, dateOfRequest, startTime,
                        endTime, SlotStatus.SUBMITTED)
                .size()
                + repository.findByEntityIdAndDateOfRequestAndStartTimeAndEndTimeAndStatus(entityId, dateOfRequest,
                        startTime, endTime, SlotStatus.APPROVED).size();
        if (unavailableCapacityForTheSlot < entityService.findEntityById(entityId).get().getCapacityPerSlot()) {
            return true;
        }
        return false;
    }

    public List<SlotRequest> getAllSlotRequestsForUserEmail() {
        List<SlotRequest> userSlots = repository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());      
        Collections.sort(userSlots);
        return userSlots;
    }

    public List<SlotRequest> findByEntityIdAndDateOfRequest(String entityId, String date) {
        List<SlotRequest> submittedSlots = repository.findByEntityIdAndDateOfRequestAndStatus(entityId, date, SlotStatus.SUBMITTED);
        List<SlotRequest> approvedSlots = repository.findByEntityIdAndDateOfRequestAndStatus(entityId, date, SlotStatus.APPROVED);
        return Stream.concat(submittedSlots.stream(), approvedSlots.stream())
        .collect(Collectors.toList());
    }

    public SlotRequest cancelSlot(String id) {
            SlotRequest slotRequest = repository.findById(id).get();
            slotRequest.setStatus(SlotStatus.CANCELLED);
            return repository.save(slotRequest);
    }
}
