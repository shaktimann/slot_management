package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "sr", type = "slotRequest")
@Data
public class SlotRequest {

    @Id
    private String id;
    private long startTime;
    private long endTime;
    private String entityId;
    private String userId;
    private SlotStatus status;
}
