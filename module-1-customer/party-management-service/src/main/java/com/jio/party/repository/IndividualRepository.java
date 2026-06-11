package com.jio.party.repository;

import com.jio.party.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, String> {

    /** Find by family name (case-insensitive) */
    List<Individual> findByFamilyNameIgnoreCase(String familyName);

    /** Find by exact status (e.g. "validated") */
    List<Individual> findByStatus(String status);

    /** Find by nationality */
    List<Individual> findByNationality(String nationality);
}
