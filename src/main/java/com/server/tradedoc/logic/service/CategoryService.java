package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.dto.CategoryDTO;
import com.server.tradedoc.logic.dto.reponse.CountResponse;
import com.server.tradedoc.logic.dto.reponse.DeleteResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * CategoryService
 *
 * @author DatDV
 */
public interface CategoryService {
    CategoryDTO createOrUpdateCategory(CategoryDTO categoryDTO);
    DeleteResponse deleteCategory(List<Long> ids);
    List<CategoryDTO> showAllCategory(SearchCategoryBuilder builder , Pageable pageable);
    CategoryDTO findById(Long id);
    CountResponse count(SearchCategoryBuilder builder);
}
