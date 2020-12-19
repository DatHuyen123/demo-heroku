package com.server.tradedoc.logic.repository.custom.impl;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.entity.CategoryEntity;
import com.server.tradedoc.logic.repository.custom.CategoryRepositoryCustom;
import com.server.tradedoc.utils.BuildMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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

    @Autowired
    private BuildMapUtils buildMapUtils;

    @Override
    public List<CategoryEntity> findCategoryUseByIdIn(List<Long> ids) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM category c INNER JOIN product_category pc ON c.id = pc.categoryid ");
        sql.append("WHERE 1=1 AND c.id IN (:idCategoryUse)");
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idCategoryUse", ids);
        return this.getResultList(sql.toString(), parameter, CategoryEntity.class);
    }

    @Override
    public List<CategoryEntity> findAllCategory(SearchCategoryBuilder builder, Pageable pageable) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.id AS category_id,\n" +
                "\tA.createdby AS created_by,\n" +
                "\tA.createddate AS created_date,\n" +
                "\tA.modifiedby AS modified_by,\n" +
                "\tA.modifieddate AS modified_date,\n" +
                "\tA.code AS category_code,\n" +
                "\tA.name AS category_name FROM category A ");
        sql.append("WHERE 1=1 ");
        if (!builder.getName().equals("")) {
            sql.append("   AND LOWER(A.name) LIKE :name ");
        }
        return this.getResultList(sql.toString(), parameter, "findAllCategory", pageable);
    }

    @Override
    public Long countCategory(SearchCategoryBuilder builder) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM category A ");
        sql.append("WHERE 1=1 ");
        if (!builder.getName().equals("")) {
            sql.append("   AND LOWER(A.name) LIKE LOWER(:name) ");
        }
        Long count = Long.parseLong(this.getSingleResult(sql.toString() , parameter).toString());
        return count;
    }
}
