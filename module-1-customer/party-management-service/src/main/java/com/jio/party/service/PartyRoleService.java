package com.jio.party.service;

import com.jio.party.model.PartyRole;
import com.jio.party.repository.PartyRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PartyRoleService {

    private final PartyRoleRepository repository;

    public PartyRoleService(PartyRoleRepository repository) {
        this.repository = repository;
    }

    /** Create a new party role */
    public PartyRole create(PartyRole partyRole) {
        if (partyRole.getStatus() == null || partyRole.getStatus().isBlank()) {
            partyRole.setStatus("initialized");
        }
        return repository.save(partyRole);
    }

    /** List all party roles */
    @Transactional(readOnly = true)
    public List<PartyRole> findAll() {
        return repository.findAll();
    }

    /** Find by id */
    @Transactional(readOnly = true)
    public Optional<PartyRole> findById(String id) {
        return repository.findById(id);
    }

    /** Find all roles for a specific party */
    @Transactional(readOnly = true)
    public List<PartyRole> findByEngagedPartyId(String engagedPartyId) {
        return repository.findByEngagedPartyId(engagedPartyId);
    }

    /**
     * Partial update (PATCH semantics)
     */
    public Optional<PartyRole> patch(String id, PartyRole patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getName()             != null) existing.setName(patch.getName());
            if (patch.getRoleType()         != null) existing.setRoleType(patch.getRoleType());
            if (patch.getDescription()      != null) existing.setDescription(patch.getDescription());
            if (patch.getStatus()           != null) existing.setStatus(patch.getStatus());
            if (patch.getStatusReason()     != null) existing.setStatusReason(patch.getStatusReason());
            if (patch.getEngagedPartyId()   != null) existing.setEngagedPartyId(patch.getEngagedPartyId());
            if (patch.getEngagedPartyHref() != null) existing.setEngagedPartyHref(patch.getEngagedPartyHref());
            if (patch.getEngagedPartyName() != null) existing.setEngagedPartyName(patch.getEngagedPartyName());
            if (patch.getEngagedPartyType() != null) existing.setEngagedPartyType(patch.getEngagedPartyType());
            if (patch.getValidFor()         != null) existing.setValidFor(patch.getValidFor());
            return repository.save(existing);
        });
    }

    /** Delete by id */
    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
