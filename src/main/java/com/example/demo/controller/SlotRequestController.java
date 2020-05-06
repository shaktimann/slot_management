package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SlotRequest;
import com.example.demo.services.SlotRequestService;

@RestController
@RequestMapping("/api/slotRequest")
public class SlotRequestController {
    
    @Autowired
    private SlotRequestService slotRequestService;
    
    @RequestMapping(value="all",method = RequestMethod.GET)
    public List<SlotRequest> getSlotRequests(){
        return slotRequestService.getAll();
    } 
    
    @RequestMapping(value="user",method = RequestMethod.GET)
    public List<SlotRequest> getUserSlotRequests(){
        return slotRequestService.getAllSlotRequestsForUserEmail();
    }

    @RequestMapping(value="remove/{id}",method = RequestMethod.GET)
    public void remove(@PathVariable String id){
        slotRequestService.remove(id);
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public SlotRequest save(@RequestBody SlotRequest slotRequest) {
        return slotRequestService.save(slotRequest);
    }
    
    @RequestMapping(value = "/cancel/{id}",method = RequestMethod.PUT)
    public SlotRequest cancelASlot(@PathVariable String id) {
        return slotRequestService.cancelSlot(id);
    }
    
    @RequestMapping(value="entity/{entityId}",method = RequestMethod.GET)
    public List<SlotRequest> getTotalSlotBookingsForADate(@PathVariable String entityId, @RequestParam String date){
        return slotRequestService.findByEntityIdAndDateOfRequest(entityId, date);
    }
    
}
