package com.demo.vnpt.repository.custom.impl;

import com.demo.vnpt.enitty.ProductsEntity;
import com.demo.vnpt.repository.custom.ProductsRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class ProductsRepositoryImpl implements ProductsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductsEntity> findAll(Map<String, Object> searchPrams) {
        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
        sql.append(createSql(searchPrams));
        sql.append(" ORDER BY id ASC ");
        Query query = entityManager.createNativeQuery(sql.toString() , ProductsEntity.class);
        return query.getResultList();
    }

    private String createSql(Map<String, Object> searchPrams){
        StringBuilder sql = new StringBuilder("");
        if(searchPrams != null){
            searchPrams.forEach((key , value) -> {
                if(value != null && value instanceof Long){
                    sql.append(" AND "+key+" = "+value+"");
                }
                if(value != null && value instanceof String){
                    sql.append(" AND LOWER("+key+") LIKE '%"+((String) value).toLowerCase()+"%' ");
                }
            });
        }
        return sql.toString();
    }
}
