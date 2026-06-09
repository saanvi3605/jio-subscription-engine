package com.jio.subscription.payments.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jio.subscription.payments.AbstractIntegrationTest;
import com.jio.subscription.payments.domain.PaymentEntity;
import com.jio.subscription.payments.domain.PaymentStateTransition;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

class PaymentPersistenceIT extends AbstractIntegrationTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentStateTransitionRepository transitionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PaymentEntity newPayment(String correlator) {
        PaymentEntity payment = new PaymentEntity();
        payment.setId(UUID.randomUUID().toString());
        payment.setCorrelatorId(correlator);
        payment.setStatus("initiated");
        payment.setTotalAmountValue(new BigDecimal("249.0000"));
        payment.setTotalAmountCurrency("INR");
        payment.setAccountId("acct-1");
        payment.setPaymentMethodId("pm-1");
        return payment;
    }

    @Test
    void persistsAndReadsBackByCorrelator() {
        PaymentEntity saved = paymentRepository.saveAndFlush(newPayment("corr-it-1"));

        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(paymentRepository.findByCorrelatorId("corr-it-1"))
                .get()
                .satisfies(p -> {
                    assertThat(p.getId()).isEqualTo(saved.getId());
                    assertThat(p.getTotalAmountValue()).isEqualByComparingTo("249");
                    assertThat(p.getStatus()).isEqualTo("initiated");
                });
    }

    @Test
    void duplicateCorrelatorIsRejectedByUniqueIndex() {
        paymentRepository.saveAndFlush(newPayment("corr-dup"));

        assertThatThrownBy(() -> paymentRepository.saveAndFlush(newPayment("corr-dup")))
                .isInstanceOf(Exception.class);
    }

    @Test
    void stateTransitionTableIsAppendOnly() {
        PaymentEntity payment = paymentRepository.saveAndFlush(newPayment("corr-append"));
        PaymentStateTransition transition = transitionRepository.saveAndFlush(
                new PaymentStateTransition(payment.getId(), null, "initiated", "created", "corr-append"));

        assertThat(transition.getId()).isNotNull();

        assertThatThrownBy(() -> jdbcTemplate.update(
                "UPDATE payment_state_transition SET reason = ? WHERE id = ?",
                "tampered", transition.getId()))
                .hasMessageContaining("append-only");

        assertThatThrownBy(() -> jdbcTemplate.update(
                "DELETE FROM payment_state_transition WHERE id = ?", transition.getId()))
                .hasMessageContaining("append-only");
    }
}
