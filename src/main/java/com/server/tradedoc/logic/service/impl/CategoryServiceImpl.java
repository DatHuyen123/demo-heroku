package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.converter.CategoryConverter;
import com.server.tradedoc.logic.dto.CategoryDTO;
import com.server.tradedoc.logic.entity.CategoryEntity;
import com.server.tradedoc.logic.repository.CategoryRepository;
import com.server.tradedoc.logic.service.CategoryService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CategoryServiceImpl
 *
 * @author DatDV
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    @Transactional
    public CategoryDTO createOrUpdateCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getName().equals("") || categoryDTO.getName() == null) {
            throw new CustomException("category name not null", CommonUtils.putError("categoryDTO", "ERR_0034"));
        }
        categoryDTO.setCode(categoryDTO.getName().toUpperCase().trim().replace(" ", "_"));
        return categoryConverter.toDto(categoryRepository.save(categoryConverter.toEntity(categoryDTO)));
    }

    @Override
    @Transactional
    public void deleteCategory(List<Long> ids) {
        List<CategoryEntity> categoryEntities = categoryRepository.findCategoryUseByIdIn(ids);
        if (!categoryEntities.isEmpty()) {
            throw new CustomException("Catelogy is being used", CommonUtils.putError("ids", "ERR_0030"));
        }
        List<CategoryEntity> categoryEntityList = categoryRepository.findCategoryEntitiesByIdIn(ids);
        if (categoryEntityList.isEmpty()) {
            throw new CustomException("category not find", CommonUtils.putError("ids", "ERR_0030"));
        }
        for (Long id : ids) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public List<CategoryDTO> showAllCategory(SearchCategoryBuilder builder, Pageable pageable) {
        List<CategoryEntity> categoryEntities = categoryRepository.findAllCategory(builder, pageable);
        return categoryConverter.toListDto(categoryEntities);
    }

}
