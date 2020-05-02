package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Document(indexName = "sd", type = "user")
@Data
public class User {

	@Id
    private String id;
	private String name;
	private String email;
	private String userType;
	private String locationX;
	private String entityId;

	// slots booked - list [entity, time]
	//(pre-populate)
	private String fixedSeatingCode;
	private String managerUserId;
	private Map<String, Integer> entityMaxLimit;
	private List<String> neighbours;
}
