package com.jio.subscription.payments.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.jio.payments.tmf676.model.AccountRef;
import com.jio.payments.tmf676.model.Money;
import com.jio.payments.tmf676.model.Payment;
import com.jio.payments.tmf676.model.PaymentCreate;
import com.jio.payments.tmf676.model.PaymentMethodRefOrValue;
import com.jio.subscription.payments.domain.PaymentEntity;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PaymentMapperTest {

    private final PaymentMapper mapper = new PaymentMapperImpl(new MoneyMapper());

    private PaymentCreate sampleCreate() {
        PaymentCreate create = new PaymentCreate();
        create.setCorrelatorId("corr-1");
        create.setName("Order 42");
        create.setDescription("monthly plan");
        create.setAuthorizationCode("AUTH123");
        create.setAccount(new AccountRef("acct-9"));
        create.setPaymentMethod(new PaymentMethodRefOrValue().id("pm-7"));
        create.setTotalAmount(new Money().value(100.5f).unit("INR"));
        create.setTaxAmount(new Money().value(15.0f).unit("INR"));
        return create;
    }

    @Test
    void createMapsScalarAndMoneyFieldsToEntity() {
        PaymentEntity entity = mapper.toEntity(sampleCreate());

        assertThat(entity.getCorrelatorId()).isEqualTo("corr-1");
        assertThat(entity.getName()).isEqualTo("Order 42");
        assertThat(entity.getDescription()).isEqualTo("monthly plan");
        assertThat(entity.getAuthorizationCode()).isEqualTo("AUTH123");
        assertThat(entity.getAccountId()).isEqualTo("acct-9");
        assertThat(entity.getPaymentMethodId()).isEqualTo("pm-7");
        assertThat(entity.getTotalAmountValue()).isEqualByComparingTo(new BigDecimal("100.5"));
        assertThat(entity.getTotalAmountCurrency()).isEqualTo("INR");
        assertThat(entity.getTaxAmountValue()).isEqualByComparingTo(new BigDecimal("15.0"));
    }

    @Test
    void entityMapsBackToDtoPreservingMoney() {
        PaymentEntity entity = mapper.toEntity(sampleCreate());
        entity.setId("pay-1");
        entity.setStatus("captured");

        Payment dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo("pay-1");
        assertThat(dto.getStatus()).isEqualTo("captured");
        assertThat(dto.getCorrelatorId()).isEqualTo("corr-1");
        assertThat(dto.getTotalAmount().getValue()).isEqualTo(100.5f);
        assertThat(dto.getTotalAmount().getUnit()).isEqualTo("INR");
        assertThat(dto.getTaxAmount().getValue()).isEqualTo(15.0f);
    }

    @Test
    void nullOptionalMoneyDoesNotFailEntityMapping() {
        PaymentCreate create = sampleCreate();
        create.setAmount(null);

        PaymentEntity entity = mapper.toEntity(create);

        assertThat(entity.getAmountValue()).isNull();
        assertThat(entity.getAmountCurrency()).isNull();
    }
}
