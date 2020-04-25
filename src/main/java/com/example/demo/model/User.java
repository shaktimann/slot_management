package com.example.demo.model;

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
}
