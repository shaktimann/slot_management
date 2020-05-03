package com.example.demo.services;

import com.example.demo.model.Notification;
import com.example.demo.repos.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository repository;

    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    public List<Notification> getAll(String userId) {
        Iterable<Notification> iterable = repository.findByUserId(userId);
        List<Notification> notifications = new ArrayList<>();
        iterable.forEach(new Consumer<Notification>() {
            @Override
            public void accept(Notification t) {
                notifications.add(t);
            }

        });
        return notifications;
    }

    public void addNotification(String message, String userId)
    {
        Notification n = new Notification(message, userId);
        save(n);
    }
}
