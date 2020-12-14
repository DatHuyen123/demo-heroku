package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * CategoryService
 *
 * @author DatDV
 */
public interface CategoryService {
    CategoryDTO createOrUpdateCategory(CategoryDTO categoryDTO);
    void deleteCategory(List<Long> ids);
    List<CategoryDTO> showAllCategory(SearchCategoryBuilder builder , Pageable pageable);
    CategoryDTO findById(Long id);
}
