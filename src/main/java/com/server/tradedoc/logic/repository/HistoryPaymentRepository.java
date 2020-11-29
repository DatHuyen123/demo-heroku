package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.HistoryPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * HistoryPaymentRepository
 *
 * @author DatDV
 */
@Repository
public interface HistoryPaymentRepository extends JpaRepository<HistoryPaymentEntity , Long> {
}
