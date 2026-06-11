package com.jio.party.service;

import com.jio.party.model.Individual;
import com.jio.party.repository.IndividualRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IndividualService {

    private final IndividualRepository repository;

    public IndividualService(IndividualRepository repository) {
        this.repository = repository;
    }

    /** Create a new individual. Sets createdAt timestamp and default status. */
    public Individual create(Individual individual) {
        individual.setCreatedAt(OffsetDateTime.now());
        if (individual.getStatus() == null || individual.getStatus().isBlank()) {
            individual.setStatus("initialized");
        }
        // Build fullName if not provided
        if (individual.getFullName() == null || individual.getFullName().isBlank()) {
            String full = "";
            if (individual.getTitle()      != null) full += individual.getTitle() + " ";
            if (individual.getGivenName()  != null) full += individual.getGivenName() + " ";
            if (individual.getMiddleName() != null) full += individual.getMiddleName() + " ";
            if (individual.getFamilyName() != null) full += individual.getFamilyName();
            individual.setFullName(full.trim());
        }
        return repository.save(individual);
    }

    /** List all individuals */
    @Transactional(readOnly = true)
    public List<Individual> findAll() {
        return repository.findAll();
    }

    /** Find by id */
    @Transactional(readOnly = true)
    public Optional<Individual> findById(String id) {
        return repository.findById(id);
    }

    /**
     * Partial update (PATCH semantics) — only non-null fields from the patch
     * object are applied to the existing record.
     */
    public Optional<Individual> patch(String id, Individual patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getTitle()         != null) existing.setTitle(patch.getTitle());
            if (patch.getGivenName()     != null) existing.setGivenName(patch.getGivenName());
            if (patch.getMiddleName()    != null) existing.setMiddleName(patch.getMiddleName());
            if (patch.getFamilyName()    != null) existing.setFamilyName(patch.getFamilyName());
            if (patch.getFullName()      != null) existing.setFullName(patch.getFullName());
            if (patch.getBirthDate()     != null) existing.setBirthDate(patch.getBirthDate());
            if (patch.getGender()        != null) existing.setGender(patch.getGender());
            if (patch.getMaritalStatus() != null) existing.setMaritalStatus(patch.getMaritalStatus());
            if (patch.getNationality()   != null) existing.setNationality(patch.getNationality());
            if (patch.getPlaceOfBirth()  != null) existing.setPlaceOfBirth(patch.getPlaceOfBirth());
            if (patch.getCountryOfBirth()!= null) existing.setCountryOfBirth(patch.getCountryOfBirth());
            if (patch.getStatus()        != null) existing.setStatus(patch.getStatus());
            if (!patch.getContactMedium().isEmpty()) existing.setContactMedium(patch.getContactMedium());
            if (!patch.getIdentification().isEmpty()) existing.setIdentification(patch.getIdentification());
            existing.setUpdatedAt(OffsetDateTime.now());
            return repository.save(existing);
        });
    }

    /** Delete by id — returns false if not found */
    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
