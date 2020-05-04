package com.example.demo.controller;

import com.example.demo.model.SlotRequest;
import com.example.demo.services.SlotRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slotRequest")
public class SlotRequestController {
    @Autowired
    private SlotRequestService slotRequestService;

    @RequestMapping(value="user/{userId}",method = RequestMethod.GET)
    public List<SlotRequest> getUserSlotRequests(@PathVariable String userId){
        return slotRequestService.getAll(userId);
    }

    @RequestMapping(value="remove/{id}",method = RequestMethod.GET)
    public void remove(@PathVariable String id){
        slotRequestService.remove(id);
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public SlotRequest save(@RequestBody SlotRequest slotRequest) {
        // check if capacity for that slot duration is available
        return slotRequestService.save(slotRequest);
    }

}
