package com.example.demo.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "en", type = "entity")
@Data
public class Entity {

    @Id
    private String id;
    private String name;
    private String organizationId;

    private double geoLat;
    private double geoLong;

    private String address;

    private List<User> admins;

    private long openingTime;
    private long closingTime;
    
    private Map<Long, Long> breakDurations;

    private long expirationTime;
    private long slotDuration;
    private int capacityPerSlot;
 
    private List<Date> closedDates;
    private List<WorkingDay> workingDays;

    private SanitizationCycle sanitizationCadence;
    private EntityType type;

}
