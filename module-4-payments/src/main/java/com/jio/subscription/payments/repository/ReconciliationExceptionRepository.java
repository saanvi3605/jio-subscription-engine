package com.jio.subscription.payments.repository;

import com.jio.subscription.payments.domain.ReconciliationException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReconciliationExceptionRepository extends JpaRepository<ReconciliationException, Long> {

    List<ReconciliationException> findByResolvedFalse();
}
