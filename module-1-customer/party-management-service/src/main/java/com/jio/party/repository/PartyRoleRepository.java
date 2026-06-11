package com.jio.party.repository;

import com.jio.party.model.PartyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRoleRepository extends JpaRepository<PartyRole, String> {

    /** Get all roles held by a specific individual/org */
    List<PartyRole> findByEngagedPartyId(String engagedPartyId);

    /** Get all roles of a given type, e.g. all "ContentProvider" roles */
    List<PartyRole> findByName(String name);

    /** Get all roles with a given status */
    List<PartyRole> findByStatus(String status);
}
