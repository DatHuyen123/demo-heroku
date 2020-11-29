package com.server.tradedoc.logic.converter;

import com.server.tradedoc.logic.dto.ProductsDTO;
import com.server.tradedoc.logic.entity.ProductsEntity;
import org.springframework.stereotype.Component;

/**
 * ProductsConverter
 *
 * @author DatDV
 */
@Component
public class ProductsConverter extends MapperManager<ProductsDTO , ProductsEntity> {

}
