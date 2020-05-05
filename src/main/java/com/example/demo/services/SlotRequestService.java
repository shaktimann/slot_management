package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
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

    public SlotRequest save(SlotRequest slotRequest) {
        // check if capacity for that slot duration is available
        if(checkIfSlotIsAvailable(slotRequest.getEntityId(), slotRequest.getStartTime(), slotRequest.getEndTime())) {
            slotRequest.setStatus(SlotStatus.APPROVED);
            notificationService.addNotification("Slot Request approved", slotRequest.getUserId());
        } else {
            slotRequest.setStatus(SlotStatus.REJECTED);
            notificationService.addNotification("Slot Request rejected", slotRequest.getUserId());
        }
        SlotRequest sr =  repository.save(slotRequest);
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
        Iterable<SlotRequest> iterable = repository.findByUserId(userId);
        List<SlotRequest> slotRequests = new ArrayList<>();
        iterable.forEach(new Consumer<SlotRequest>() {
            @Override
            public void accept(SlotRequest t) {
                slotRequests.add(t);
            }

        });
        return slotRequests;
    }
    
    public boolean checkIfSlotIsAvailable(String entityId, long startTime, long endTime) {
        int unavailableCapacityForTheSlot = repository
                .findByEntityIdAndStartTimeAndEndTimeAndStatus(entityId, startTime, endTime,
                        SlotStatus.SUBMITTED)
                .size()
                + repository.findByEntityIdAndStartTimeAndEndTimeAndStatus(entityId, startTime, endTime,
                        SlotStatus.APPROVED).size();
        if(unavailableCapacityForTheSlot < entityService.findEntityById(entityId).get().getCapacityPerSlot()) {
            return true;
        }
        return false;
    }
}
