package com.jio.subscription.payments.mapper;

import com.jio.payments.tmf670.model.AccountRef;
import com.jio.payments.tmf670.model.PaymentMethod;
import com.jio.payments.tmf670.model.PaymentMethodCreate;
import com.jio.subscription.payments.domain.PaymentMethodEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface PaymentMethodMapper {

    @Mapping(target = "type", source = "atType")
    @Mapping(target = "preferred", source = "isPreferred")
    @Mapping(target = "accountId", source = "account", qualifiedByName = "firstAccountId")
    @Mapping(target = "validForStart", source = "validFor.startDateTime")
    @Mapping(target = "validForEnd", source = "validFor.endDateTime")
    PaymentMethodEntity toEntity(PaymentMethodCreate dto);

    @Mapping(target = "atType", source = "type", qualifiedByName = "stringToAtType")
    @Mapping(target = "isPreferred", source = "preferred")
    @Mapping(target = "validFor.startDateTime", source = "validForStart")
    @Mapping(target = "validFor.endDateTime", source = "validForEnd")
    @Mapping(target = "account", ignore = true)
    PaymentMethod toDto(PaymentMethodEntity entity);

    @Named("firstAccountId")
    default String firstAccountId(List<AccountRef> accounts) {
        return (accounts == null || accounts.isEmpty()) ? null : accounts.get(0).getId();
    }

    /** Lenient string -&gt; enum; unknown values map to null rather than throwing. */
    @Named("stringToAtType")
    default PaymentMethod.AtTypeEnum stringToAtType(String value) {
        if (value == null) {
            return null;
        }
        for (PaymentMethod.AtTypeEnum candidate : PaymentMethod.AtTypeEnum.values()) {
            if (candidate.getValue().equals(value)) {
                return candidate;
            }
        }
        return null;
    }
}
