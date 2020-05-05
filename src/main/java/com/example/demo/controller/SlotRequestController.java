package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        return slotRequestService.save(slotRequest);
    }
    
    public void helloWorld() {
    
    }

}
