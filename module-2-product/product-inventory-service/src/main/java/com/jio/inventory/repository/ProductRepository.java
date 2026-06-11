package com.jio.inventory.repository;

import com.jio.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    /** All products belonging to a customer — main query for "what does this customer have?" */
    List<Product> findByCustomerId(String customerId);

    /** Filter by lifecycle status: active, inactive, suspended, terminated */
    List<Product> findByStatus(String status);

    /** All inventory items created from a specific product order */
    List<Product> findByProductOrderId(String productOrderId);

    /** All inventory items of a specific product offering (plan type) */
    List<Product> findByProductOfferingId(String productOfferingId);

    /** Customer + status combined filter — e.g. "all active products for customer X" */
    List<Product> findByCustomerIdAndStatus(String customerId, String status);
}
