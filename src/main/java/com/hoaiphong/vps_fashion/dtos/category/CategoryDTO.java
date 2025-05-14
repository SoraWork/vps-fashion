package com.hoaiphong.vps_fashion.dtos.category;

import java.util.List;
import java.util.UUID;

import com.hoaiphong.vps_fashion.dtos.product.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private UUID id;
    private String name;
    private List<ProductDTO> products;

}
