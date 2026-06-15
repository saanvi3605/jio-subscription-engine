package com.jio.payment.repository;

import com.jio.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    /** All payments made by a customer */
    List<Payment> findByCustomerId(String customerId);

    /** All payments against a specific billing account */
    List<Payment> findByCustomerAccountId(String customerAccountId);

    /** All payments for a specific product */
    List<Payment> findByProductId(String productId);

    /** Filter by status: received, settled, rejected, held */
    List<Payment> findByStatus(String status);

    /** All payments by a customer filtered by status */
    List<Payment> findByCustomerIdAndStatus(String customerId, String status);

    /** All payments by payment method type: UPI, CREDIT_CARD, etc. */
    List<Payment> findByPaymentMethodType(String paymentMethodType);

    /** Find a payment by its Razorpay order ID — used by the webhook handler */
    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);
}
