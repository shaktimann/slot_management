package com.example.demo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;


/* how to use slots here ?
 * Not sure if we want to use enum for slots
 * */
 

@Document(indexName = "entityDoc", type = "entity")
@Data
public class Entity {

    @Id
    private String id;
    private String organizationId;
    
    private double geolat;
    private double geolong; 
    
    private String address;
    
    private List<User> admins;
    private List<User> memberUsers;
    
    private Timestamp openingTime;
    private Timestamp closingTime;
    private Duration slotDuration;
    private int capacityPerSlot;
    private Map<Timestamp, Timestamp> breakDurations;
    private List<Date> closedDates;
    
    
}
