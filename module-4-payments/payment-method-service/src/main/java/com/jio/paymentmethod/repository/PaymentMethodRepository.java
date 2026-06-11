package com.jio.paymentmethod.repository;

import com.jio.paymentmethod.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {

    /** All payment methods registered by a customer */
    List<PaymentMethod> findByCustomerId(String customerId);

    /** Find the customer's default payment method */
    Optional<PaymentMethod> findByCustomerIdAndIsDefaultTrue(String customerId);

    /** Filter by type: UPI, CREDIT_CARD, DEBIT_CARD, NET_BANKING, WALLET */
    List<PaymentMethod> findByCustomerIdAndType(String customerId, String type);

    /** Filter by status: active, inactive, expired */
    List<PaymentMethod> findByCustomerIdAndStatus(String customerId, String status);
}
