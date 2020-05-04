package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Entity;
import com.example.demo.services.EntityService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/entity")
public class EntityController {

    @Autowired
    private EntityService entityService;

    @Autowired
    private UserService userService;

    @RequestMapping("/items")
    public List<Entity> getAll() {
        return entityService.getAll();
    }

    @RequestMapping("/items/search")
    public List<Entity> getAllWithinDistance(@RequestParam String userId, @RequestParam int distance) {
        return entityService.getEntitiesByDistancefromAUser(userService.findUserById(userId).get(), distance);
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

//    @RequestMapping(value = "/slots/{id}")
//    public Map<Timestamp, Timestamp> getAllSlotsForADay(@PathVariable String id) {
//        return entityService.getAllSlotsInADay(entityService.findEntityById(id).get());
//    }
    
    @RequestMapping(value = "/slots/{id}")
    public Map<LocalTime, LocalTime> getAllSlotsForADay(@PathVariable String id) {
        return entityService.getAllSlotsInADay(entityService.findEntityById(id).get());
    }

}
