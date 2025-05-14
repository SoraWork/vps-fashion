package com.hoaiphong.vps_fashion.dtos.product;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private String image;
    private Double price;
}