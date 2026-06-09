package com.jio.subscription.payments.mapper;

import com.jio.payments.tmf676.model.Refund;
import com.jio.payments.tmf676.model.RefundCreate;
import com.jio.subscription.payments.domain.RefundEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = MoneyMapper.class)
public interface RefundMapper {

    @Mapping(target = "amountValue", source = "amount.value", qualifiedByName = "floatToBigDecimal")
    @Mapping(target = "amountCurrency", source = "amount.unit")
    @Mapping(target = "totalAmountValue", source = "totalAmount.value", qualifiedByName = "floatToBigDecimal")
    @Mapping(target = "totalAmountCurrency", source = "totalAmount.unit")
    @Mapping(target = "taxAmountValue", source = "taxAmount.value", qualifiedByName = "floatToBigDecimal")
    @Mapping(target = "taxAmountCurrency", source = "taxAmount.unit")
    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "paymentMethodId", source = "paymentMethod.id")
    @Mapping(target = "paymentId", source = "payment.id")
    RefundEntity toEntity(RefundCreate dto);

    @Mapping(target = "amount.value", source = "amountValue", qualifiedByName = "bigDecimalToFloat")
    @Mapping(target = "amount.unit", source = "amountCurrency")
    @Mapping(target = "totalAmount.value", source = "totalAmountValue", qualifiedByName = "bigDecimalToFloat")
    @Mapping(target = "totalAmount.unit", source = "totalAmountCurrency")
    @Mapping(target = "taxAmount.value", source = "taxAmountValue", qualifiedByName = "bigDecimalToFloat")
    @Mapping(target = "taxAmount.unit", source = "taxAmountCurrency")
    @Mapping(target = "payment.id", source = "paymentId")
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "paymentMethod", ignore = true)
    Refund toDto(RefundEntity entity);
}
