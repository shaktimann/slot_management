package com.example.demo.controller;

import com.example.demo.model.SlotRequest;
import com.example.demo.model.User;
import com.example.demo.services.SlotRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/slotRequest")
public class SlotRequestController {
    @Autowired
    private SlotRequestService slotRequestService;

    @RequestMapping(value="/user/${userId}",method = RequestMethod.GET)
    public List<SlotRequest> getUserSlotRequests(String userId){
        return slotRequestService.getAll(userId);
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public SlotRequest save(@RequestBody SlotRequest slotRequest) {
        return slotRequestService.save(slotRequest);
    }

}
