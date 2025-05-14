package com.hoaiphong.vps_fashion.services;

import java.util.List;
import java.util.UUID;

import com.hoaiphong.vps_fashion.dtos.category.CategoryDTO;
import com.hoaiphong.vps_fashion.dtos.product.ProductDTO;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getCategoriesLimited();

    CategoryDTO getFullCategoryById(UUID id);
    
    CategoryDTO createCategory(String name, List<ProductDTO> products);
}
