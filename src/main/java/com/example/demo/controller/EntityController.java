package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Entity;
import com.example.demo.services.EntityService;
import com.example.demo.services.SlotRequestService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/entity")
public class EntityController {

    @Autowired
    private EntityService entityService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private SlotRequestService slotRequestService;

    @RequestMapping("/items")
    public List<Entity> getAll() {
        return entityService.getAll();
    }

    @RequestMapping("/items/search")
    public List<Entity> getAllWithinDistance(@RequestParam int distance) {
       // return entityService.getEntitiesByDistancefromAUser(userService.findUserById(userId).get(), distance);
        return entityService.getEntitiesByDistancefromAUser(
                userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get(),
                distance);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Entity save(@RequestBody Entity entity) {
        return entityService.save(entity);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
    Entity updateEntity(@RequestBody Entity newEntity, @PathVariable String id) {
        return entityService.update(id, newEntity);
    }

    @RequestMapping(value = "/items/{id}")
    public Optional<Entity> getEntityById(@PathVariable String id) {
        return entityService.findEntityById(id);
    }

    @RequestMapping(value = "/capacity/{id}")
    public int getCapacityByEntityId(@PathVariable String id) {
        return entityService.gettotalCapacityInADay(entityService.findEntityById(id).get());
    }
    
    @RequestMapping(value = "/slots/{id}")
    public Map<Long, Long> getAllSlotsForADay(@PathVariable String id) {
        return entityService.getAllSlotsInADay(entityService.findEntityById(id).get());
    }
    
    @RequestMapping(value = "/slot/current/{id}")
    public Pair<Long, Long> getCurrentSlot(@PathVariable String id) {
        return entityService.getCurrentSlot(entityService.findEntityById(id).get());
    }
    
    @RequestMapping(value = "slot/availability/{id}")
    public boolean checkIfSlotIsAvailable(@PathVariable String id, @RequestParam String date, @RequestParam long startTime,
            @RequestParam long endTime) {
        return slotRequestService.checkIfSlotIsAvailable(id, date, startTime, endTime);
    }
    
    @RequestMapping(value = "slots/availability/{id}")
    public Map<Long, Long> getAllAvailableSlotsForADate(@PathVariable String id, @RequestParam String date) {
        Map<Long, Long> allSlots = entityService.getAllSlotsInADay(entityService.findEntityById(id).get());
        Map<Long, Long> allSlotsWithDates = new HashMap<Long, Long>();
        for (Map.Entry<Long, Long> slot : allSlots.entrySet()) {
            if(slotRequestService.checkIfSlotIsAvailable(id, date, slot.getKey(), slot.getValue())) {
                allSlotsWithDates.put(slot.getKey(), slot.getValue());
            }
        }
        return allSlotsWithDates;
    }    
}
