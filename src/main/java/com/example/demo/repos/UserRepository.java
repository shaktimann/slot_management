package com.example.demo.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
public interface UserRepository extends ElasticsearchRepository<User, String>{

}
