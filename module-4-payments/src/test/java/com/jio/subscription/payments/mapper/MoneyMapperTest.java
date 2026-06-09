package com.jio.subscription.payments.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class MoneyMapperTest {

    private final MoneyMapper mapper = new MoneyMapper();

    @Test
    void floatToBigDecimalUsesCanonicalDecimalString() {
        assertThat(mapper.floatToBigDecimal(0.1f)).isEqualByComparingTo(new BigDecimal("0.1"));
        assertThat(mapper.floatToBigDecimal(1234.56f)).isEqualByComparingTo(new BigDecimal("1234.56"));
    }

    @Test
    void nullsRoundTripToNull() {
        assertThat(mapper.floatToBigDecimal(null)).isNull();
        assertThat(mapper.bigDecimalToFloat(null)).isNull();
    }

    @Test
    void bigDecimalToFloat() {
        assertThat(mapper.bigDecimalToFloat(new BigDecimal("100.50"))).isEqualTo(100.5f);
    }
}
