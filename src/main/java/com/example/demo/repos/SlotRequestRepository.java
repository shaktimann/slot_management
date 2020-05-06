package com.example.demo.repos;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.SlotRequest;
import com.example.demo.model.SlotStatus;

public interface SlotRequestRepository extends ElasticsearchRepository<SlotRequest, String> {    
    
    void deleteById(String id);

    List<SlotRequest> findByEntityIdAndStartTimeAndEndTimeAndStatus(String entityId,
            long startTime, long endTime, SlotStatus status);
    
    List<SlotRequest> findByEntityIdAndDateOfRequestAndStartTimeAndEndTimeAndStatus(String entityId, String dateOfRequest,
            long startTime, long endTime, SlotStatus status);
    
    List<SlotRequest> findByUserEmail(String userEmail);
    
    List<SlotRequest> findByEntityIdAndDateOfRequestAndStatus(String entityId, String dateOfRequest, SlotStatus status);
}
