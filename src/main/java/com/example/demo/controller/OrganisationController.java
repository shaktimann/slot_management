package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Organisation;
import com.example.demo.services.OrganisationService;

@RestController
@RequestMapping("/api/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @RequestMapping("/items")
    public List<Organisation> getAll() {
        return organisationService.getAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Organisation save(@RequestBody Organisation organisation) {
        return organisationService.save(organisation);
    }

    @RequestMapping(value = "/items/{id}")
    public Optional<Organisation> getOrganisationById(@PathVariable String id) {
        return organisationService.findOrganisationById(id);
    }

}
