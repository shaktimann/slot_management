package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "not", type = "notification")
@Data @RequiredArgsConstructor @AllArgsConstructor
public class Notification {
    @Id
    private String id;
    private long time = System.currentTimeMillis();
    @NonNull private String message;
    @NonNull private String userId;
    private Boolean seen = false;
}
