package com.example.demo.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.Entity;
public interface EntityRepository extends ElasticsearchRepository<Entity, String>{

}
