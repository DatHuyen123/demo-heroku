package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomersRepository
 *
 * @author DatDV
 */
@Repository
public interface CustomersRepository extends JpaRepository<CustomersEntity , Long> {
    CustomersEntity findOneByEmail(String email);
}
