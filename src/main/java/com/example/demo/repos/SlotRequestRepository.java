package com.example.demo.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.SlotRequest;

import java.util.List;

public interface SlotRequestRepository extends ElasticsearchRepository<SlotRequest, String> {
    List<SlotRequest> findByUserId(String userId);
    void deleteById(String id);
}
