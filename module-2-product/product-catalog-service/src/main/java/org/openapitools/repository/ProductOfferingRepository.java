package org.openapitools.repository;

import org.openapitools.model.ProductOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOfferingRepository extends JpaRepository<ProductOffering, String> {
}
