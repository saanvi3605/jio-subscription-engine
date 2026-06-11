package com.jio.processflow.repository;

import com.jio.processflow.model.ProcessFlow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessFlowRepository extends JpaRepository<ProcessFlow, String> {
    List<ProcessFlow> findByDefinitionId(String definitionId);
    List<ProcessFlow> findByStatus(String status);
}
