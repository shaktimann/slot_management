package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Organisation;
import com.example.demo.repos.OrganisationRepository;

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    public Organisation save(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    public List<Organisation> getAll() {
        Iterable<Organisation> iterable = organisationRepository.findAll();
        List<Organisation> organisations = new ArrayList<>();
        iterable.forEach(new Consumer<Organisation>() {
            @Override
            public void accept(Organisation organisation) {
                organisations.add(organisation);
            }

        });
        return organisations;
    }

    public Optional<Organisation> findOrganisationById(String id) {
        return organisationRepository.findById(id);
    }

}
