package com.server.tradedoc.logic.repository.custom;

import com.server.tradedoc.logic.entity.CategoryEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * CategoryRepositoryCustom
 *
 * @author DatDV
 */
public interface CategoryRepositoryCustom {
    List<CategoryEntity> findCategoryUseByIdIn(List<Long> ids);
    List<CategoryEntity> findAllCategory(Map<String , Object> parameter , Pageable pageable);
}
