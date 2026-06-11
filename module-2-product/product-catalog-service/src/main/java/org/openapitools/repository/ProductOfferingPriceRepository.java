package org.openapitools.repository;

import org.openapitools.model.ProductOfferingPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOfferingPriceRepository extends JpaRepository<ProductOfferingPrice, String> {
}
