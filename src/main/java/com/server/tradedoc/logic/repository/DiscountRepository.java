package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DiscountRepository
 *
 * @author DatDV
 */
@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity , Long> {
    DiscountEntity findByCodeAndStatus(String code , Integer status);
    DiscountEntity findByStatus(Integer status);
    DiscountEntity findByCode(String code);
    List<DiscountEntity> findAllByStatus(Integer status);
}
