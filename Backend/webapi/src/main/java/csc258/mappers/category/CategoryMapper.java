package csc258.mappers.category;

import csc258.domain.db.category.CategoryDomain;
import csc258.domain.frontend.category.Category;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by desair4 on 4/16/2017.
 */
public class CategoryMapper {

    public static Category mapCategoryBackendToFrontend(CategoryDomain categoryDomain) {
        return new Category(categoryDomain.getCategoryId(), categoryDomain.getCategoryName());
    }

    public static List<Category> mapCategoryListBackendToFrontend(List<CategoryDomain> categoryDomainList) {
        return categoryDomainList.stream().map(CategoryMapper::mapCategoryBackendToFrontend).collect(Collectors.toList());
    }

    public static List<CategoryDomain> mapCategoryListFrontendToBackend(List<Category> categoryList) {
        if (categoryList == null) return null;
        return categoryList.stream().map(CategoryMapper::mapCategoryFrontendToBackend).collect(Collectors.toList());
    }

    public static CategoryDomain mapCategoryFrontendToBackend(Category category) {
        return new CategoryDomain(category.getCategoryId(), category.getcategoryName());
    }
}