package com.demo.vnpt.repository;

import com.demo.vnpt.enitty.ProductsEntity;
import com.demo.vnpt.repository.custom.ProductsRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> , ProductsRepositoryCustom {

    @Query(value = "SELECT pro.id " +
                   "FROM products pro " +
                   "WHERE pro.id IN (:ids)" , nativeQuery = true)
    List<Long> findByIdIn(@Param("ids") List<Long> ids);
}
