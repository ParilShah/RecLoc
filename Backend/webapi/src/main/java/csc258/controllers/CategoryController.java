package csc258.controllers;

import csc258.domain.frontend.category.Category;
import csc258.domain.frontend.category.fetchcategory.FetchCategoryResponse;
import csc258.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Paril on 4/13/2017.
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    private CategoryService categoryService;

    @RequestMapping(value = "/fetchAllCategories", method = RequestMethod.GET)
    public FetchCategoryResponse fetchAllCategories(HttpServletRequest request, HttpServletResponse response) {
        return categoryService.fetchAllCategories();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category category(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) {
        return categoryService.findCategoryByCategoryId(id);
    }

    @RequestMapping(value = "/fetchCategories", method = RequestMethod.GET)
    public List<Category> fetchCategories(@RequestBody List<Category> categoryList, HttpServletRequest request, HttpServletResponse response) {
        return categoryService.findCategoriesByCategoryIds(categoryList);
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostConstruct
    void afterPropertiesSet() {
        Assert.notNull(categoryService, "categoryService cannot be null");
    }
}