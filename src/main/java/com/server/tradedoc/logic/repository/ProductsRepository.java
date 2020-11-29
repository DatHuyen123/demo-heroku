package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductsRepository
 *
 * @author DatDV
 */
@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity , Long> {
}
