package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.CommentsEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CommentRepository
 *
 * @author DatDV
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentsEntity , Long> {
    void deleteCommentsEntityByProducts(ProductsEntity products);
}
