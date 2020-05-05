package com.example.demo.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.SlotRequest;
import com.example.demo.model.SlotStatus;

public interface SlotRequestRepository extends ElasticsearchRepository<SlotRequest, String> {
    List<SlotRequest> findByUserId(String userId);
    void deleteById(String id);

    List<SlotRequest> findByEntityIdAndStartTimeAndEndTimeAndStatus(String entityId,
            long startTime, long endTime, SlotStatus status);
}
