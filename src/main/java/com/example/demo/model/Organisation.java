package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "org", type = "organisation")
@Data
public class Organisation {
    
    @Id
    private String id;
    String name;
    String addressOfHeadOffice;
    User admin;
    
}
