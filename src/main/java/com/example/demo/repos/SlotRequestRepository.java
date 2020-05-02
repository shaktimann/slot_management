package com.example.demo.repos;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SlotRequest;

import java.util.List;

public interface SlotRequestRepository extends ElasticsearchRepository<SlotRequest, String> {
    List<SlotRequest> findByUserId(String userId);
}
