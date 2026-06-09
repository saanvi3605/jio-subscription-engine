package com.jio.subscription.payments.mapper;

import com.jio.payments.tmf676.model.Payment;
import com.jio.payments.tmf676.model.PaymentCreate;
import com.jio.subscription.payments.domain.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps between the generated TMF676 Payment DTOs and {@link PaymentEntity}. Only the flat,
 * queryable columns are mapped here; rich nested structures (payer, channel, paymentItem, full
 * account/paymentMethod) are preserved by the service layer through the {@code dtoJson} overlay.
 */
@Mapper(uses = MoneyMapper.class)
public interface PaymentMapper {

    @Mapping(target = "amountValue", source = "amount.value", qualifiedByName = "floatToBigDecimal")
    @Mapping(target = "amountCurrency", source = "amount.unit")
    @Mapping(target = "totalAmountValue", source = "totalAmount.value", qualifiedByName = "floatToBigDecimal")
    @Mapping(target = "totalAmountCurrency", source = "totalAmount.unit")
    @Mapping(target = "taxAmountValue", source = "taxAmount.value", qualifiedByName = "floatToBigDecimal")
    @Mapping(target = "taxAmountCurrency", source = "taxAmount.unit")
    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "paymentMethodId", source = "paymentMethod.id")
    PaymentEntity toEntity(PaymentCreate dto);

    @Mapping(target = "amount.value", source = "amountValue", qualifiedByName = "bigDecimalToFloat")
    @Mapping(target = "amount.unit", source = "amountCurrency")
    @Mapping(target = "totalAmount.value", source = "totalAmountValue", qualifiedByName = "bigDecimalToFloat")
    @Mapping(target = "totalAmount.unit", source = "totalAmountCurrency")
    @Mapping(target = "taxAmount.value", source = "taxAmountValue", qualifiedByName = "bigDecimalToFloat")
    @Mapping(target = "taxAmount.unit", source = "taxAmountCurrency")
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "paymentMethod", ignore = true)
    Payment toDto(PaymentEntity entity);
}
