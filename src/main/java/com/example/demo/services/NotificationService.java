package com.example.demo.services;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Notification;
import com.example.demo.repos.NotificationRepository;

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
        Notification n = new Notification(getUniqueId(),System.currentTimeMillis(),message, userId, false);
        save(n);
    }

    public String getUniqueId()
    {
        UUID uuid = UUID.randomUUID();
        byte[] src = ByteBuffer.wrap(new byte[16])
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits())
                .array();
       return Base64.getUrlEncoder().encodeToString(src).substring(0, 22);
    }
}
