package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.search.DocValueFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

import java.util.Date;

@Document(indexName = "sr", type = "slotRequest")
@Data
public class SlotRequest {

    @Id
    private String id;
    private long startTime;
    private long endTime;
    private Date bookingDate;
    private Date approvedDate;
    @Getter @Setter private String entityId;
    @Getter @Setter private String userId;
    @Getter @Setter private SlotStatus status = SlotStatus.SUBMITTED;
}
