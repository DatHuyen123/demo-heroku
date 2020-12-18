package com.server.tradedoc.logic.repository.custom;

import com.server.tradedoc.logic.builder.SearchProductBuilder;
import com.server.tradedoc.logic.dto.reponse.ProductsSearchDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductsRepositoryCustom {
    List<ProductsSearchDTO> findAllProductByCondition(SearchProductBuilder builder , Pageable pageable);
    Long countProductByCondition(SearchProductBuilder builder);
}
