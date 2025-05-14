package com.hoaiphong.vps_fashion.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hoaiphong.vps_fashion.dtos.category.CategoryDTO;
import com.hoaiphong.vps_fashion.dtos.product.ProductDTO;
import com.hoaiphong.vps_fashion.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();

        categories.add(createCategory("Đồ Nam", Arrays.asList(
            new ProductDTO(UUID.randomUUID(), "Áo Nam Tay Ngắn", "nam1.png", 179000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Len Lưng Cổ", "nam2.png", 249000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Len Nam Giản", "nam3.png", 319000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Len Nam", "nam4.png", 299000.0)
        )));

        categories.add(createCategory("Đồ Nữ", Arrays.asList(
            new ProductDTO(UUID.randomUUID(), "Áo Tay Dài Nữ", "nu1.png", 229000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Len Cổ Tim", "nu2.png", 239000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Len Tay Lửng Nữ", "nu3.png", 269000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Len Nữ", "nu4.png", 289000.0)
        )));

        categories.add(createCategory("Đồ Trẻ Em", Arrays.asList(
            new ProductDTO(UUID.randomUUID(), "Áo Nỉ Trẻ Em", "tre1.png", 199000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Tay Nỉ Ngắn", "tre2.png", 179000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Nỉ Tay Ngắn", "tre3.png", 189000.0),
            new ProductDTO(UUID.randomUUID(), "Áo Len Nữ Bé", "tre4.png", 199000.0)
        )));

        return categories;
    }

    @Override
    public CategoryDTO createCategory(String name, List<ProductDTO> products) {
        CategoryDTO category = new CategoryDTO();
        category.setId(UUID.randomUUID());
        category.setName(name);
        category.setProducts(products);
        return category;
    }

    @Override
    public List<CategoryDTO> getCategoriesLimited() {
         return getAllCategories().stream()
                .map(category -> {
                    List<ProductDTO> limited = category.getProducts().size() > 4
                            ? category.getProducts().subList(0, 4)
                            : category.getProducts();
                    return new CategoryDTO(category.getId(), category.getName(), limited);
                }).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getFullCategoryById(UUID id) {
         return getAllCategories().stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .orElse(null);
    }

}
