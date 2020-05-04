package com.example.demo.repos;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.Organisation;

public interface OrganisationRepository extends ElasticsearchRepository<Organisation, String> {

}
