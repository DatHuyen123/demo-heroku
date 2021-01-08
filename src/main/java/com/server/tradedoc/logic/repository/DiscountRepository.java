package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity , Long> {
}
