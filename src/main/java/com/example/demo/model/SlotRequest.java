package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(indexName = "sr", type = "slotRequest")
@Data
public class SlotRequest {

    @Id
    private String id;
    private long startTime;
    private long endTime;
    private String dateOfRequest;
    private String bookingDate;
    private String approvedDate;
    @Getter
    @Setter
    private String entityId;
    @Getter
    @Setter
    private String userEmail;
    @Getter
    @Setter
    private SlotStatus status = SlotStatus.SUBMITTED;
}
