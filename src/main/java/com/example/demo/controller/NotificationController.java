package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value="/user/{userId}",method = RequestMethod.GET)
    public List<Notification> getNotificationsForUser(@PathVariable String userId){
        System.out.println("ID is " + userId);
        return notificationService.getAll(userId);
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Notification save(@RequestBody Notification notification) {
        return notificationService.save(notification);
    }

}