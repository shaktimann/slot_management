package com.example.demo.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "sd", type = "user")
@Data
public class User {

	@Id
    private String id;
	private String name;
	private String email;
	private String userType;
	private double geoLat;
	private double geoLong;
	private String orgId;
	private String password;
	private List<String> roles;

	// slots booked - list [entity, time]
	//(pre-populate)
	private String fixedSeatingCode;
	private String managerUserId;
	private Map<String, Integer> entityMaxLimit;
	private List<String> neighbours;
}
