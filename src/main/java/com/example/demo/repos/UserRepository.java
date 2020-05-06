package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.User;

public interface UserRepository extends ElasticsearchRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
