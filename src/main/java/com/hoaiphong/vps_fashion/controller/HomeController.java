package com.hoaiphong.vps_fashion.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoaiphong.vps_fashion.dtos.category.CategoryDTO;
import com.hoaiphong.vps_fashion.services.CategoryService;

@Controller
@RequestMapping("/")
public class HomeController {
    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        List<CategoryDTO> categories = categoryService.getCategoriesLimited();
        model.addAttribute("categories", categories);
        return "home/index";
    }

    @GetMapping("about")
    public String about() {
        return "home/about";
    }

    @GetMapping("/category/{id}")
    public String viewCategory(@PathVariable UUID id, Model model) {
        CategoryDTO category = categoryService.getFullCategoryById(id);
        model.addAttribute("category", category);
        return "category-detail";
    }


}
