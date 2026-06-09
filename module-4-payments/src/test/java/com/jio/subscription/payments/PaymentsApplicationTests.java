package com.jio.subscription.payments;

import org.junit.jupiter.api.Test;

/**
 * Boots the full application context against real Postgres/Kafka/Redis. This also exercises the
 * Flyway migration and Hibernate {@code validate}, proving the entities match the schema.
 */
class PaymentsApplicationTests extends AbstractIntegrationTest {

    @Test
    void contextLoads() {
    }
}
