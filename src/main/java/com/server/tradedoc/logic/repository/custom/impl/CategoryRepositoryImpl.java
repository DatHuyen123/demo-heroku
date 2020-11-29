package com.server.tradedoc.logic.repository.custom.impl;

import com.server.tradedoc.logic.entity.CategoryEntity;
import com.server.tradedoc.logic.repository.custom.CategoryRepositoryCustom;
import com.server.tradedoc.utils.BuildQueryUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CategoryRepositoryImpl
 *
 * @author DatDV
 */
@Repository
public class CategoryRepositoryImpl extends RepositoryCustomUtils<CategoryEntity> implements CategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryEntity> findCategoryUseByIdIn(List<Long> ids) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM category c INNER JOIN product_category pc ON c.id = pc.category_id ");
        StringBuilder idCategoryUse = new StringBuilder();
        for (Long id : ids){
            idCategoryUse.append(id + ",");
        }
        sql.append("WHERE 1=1 AND c.id IN (:idCategoryUse)");
        Map<String , Object> parameter = new HashMap<>();
        parameter.put("idCategoryUse" , idCategoryUse.toString().substring(0 , idCategoryUse.toString().length() - 1));
        return this.getResultList(sql.toString(), parameter , CategoryEntity.class);
    }

    @Override
    public List<CategoryEntity> findAllCategory(Map<String, Object> parameter, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.id AS category_id,\n" +
                "\tA.createdby AS created_by,\n" +
                "\tA.createddate AS created_date,\n" +
                "\tA.modifiedby AS modified_by,\n" +
                "\tA.modifieddate AS modified_date,\n" +
                "\tA.code AS category_code,\n" +
                "\tA.name AS category_name FROM category A ");
        sql.append("   INNER JOIN product_category B ON A.id = B.categoryid ");
        sql.append("   INNER JOIN products C ON B.productid = C.id ");
        sql.append("WHERE 1=1 ");
        sql.append("   AND B.productid = :productid ");
        sql.append("   AND LOWER(A.name) LIKE :name ");
        return this.getResultList(sql.toString() , parameter , "findAllCategory" , pageable);
    }
}