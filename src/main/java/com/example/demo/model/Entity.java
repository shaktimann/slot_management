package com.example.demo.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import ch.qos.logback.core.util.Duration;
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

    // private Timestamp openingTime;
    // private Timestamp closingTime;

    private long openingTime;
    private long closingTime;
    
    private Map<Long, Long> breakDurations;

    private long expirationTime;
    private long slotDuration;
    private int capacityPerSlot;
   
    // private Map<Timestamp, Timestamp> breakDurations;

    private List<Date> closedDates;
    private List<WorkingDay> workingDays;

    private SanitizationCycle sanitizationCadence;
    private EntityType type;

}
