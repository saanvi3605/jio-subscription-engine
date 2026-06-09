package com.jio.subscription.payments.mapper;

import java.math.BigDecimal;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 * Conversions between the TMF {@code Money.value} (a {@link Float}) and the internal
 * {@link BigDecimal} used for exact monetary arithmetic. Referenced via {@code uses} from the
 * resource mappers and selected with {@code qualifiedByName}.
 */
@Component
public class MoneyMapper {

    /**
     * Float -&gt; BigDecimal via the float's canonical decimal string, avoiding binary
     * floating-point artifacts (e.g. {@code 0.1f} becomes {@code 0.1}, not {@code 0.100000001}).
     */
    @Named("floatToBigDecimal")
    public BigDecimal floatToBigDecimal(Float value) {
        return value == null ? null : new BigDecimal(Float.toString(value));
    }

    @Named("bigDecimalToFloat")
    public Float bigDecimalToFloat(BigDecimal value) {
        return value == null ? null : value.floatValue();
    }
}
