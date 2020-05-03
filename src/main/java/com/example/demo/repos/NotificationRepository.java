package com.example.demo.repos;

import com.example.demo.model.Notification;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface NotificationRepository extends ElasticsearchRepository<Notification, String> {
    List<Notification> findByUserId(String userId);
}
