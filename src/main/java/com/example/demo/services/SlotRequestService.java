package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.example.demo.model.SlotRequest;
import com.example.demo.repos.SlotRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotRequestService {

    @Autowired
    private SlotRequestRepository repository;

    @Autowired
    private NotificationService notificationService;

    public SlotRequest save(SlotRequest slotRequest) {
        SlotRequest sr =  repository.save(slotRequest);
        notificationService.addNotification("Slot Request submitted for approval", slotRequest.getUserId());
        return sr;
    }

    public void remove(String id) {
        repository.deleteById(id);
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
}


