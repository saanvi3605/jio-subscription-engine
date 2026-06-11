package org.openapitools.repository;

import org.openapitools.model.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, String> {
}
