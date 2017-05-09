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
        if (categoryDomain == null) return null;
        return new Category(categoryDomain.getCategoryId(), categoryDomain.getCategoryName());
    }

    public static List<Category> mapCategoryListBackendToFrontend(List<CategoryDomain> categoryDomainList) {
        if (categoryDomainList == null) return null;
        return categoryDomainList.parallelStream().map(CategoryMapper::mapCategoryBackendToFrontend).collect(Collectors.toList());
    }

    public static List<CategoryDomain> mapCategoryListFrontendToBackend(List<Category> categoryList) {
        if (categoryList == null) return null;
        return categoryList.parallelStream().map(CategoryMapper::mapCategoryFrontendToBackend).collect(Collectors.toList());
    }

    public static CategoryDomain mapCategoryFrontendToBackend(Category category) {
        if (category == null) return null;
        return new CategoryDomain(category.getCategoryId(), category.getCategoryName());
    }

    public static List<CategoryDomain> mapTagListFrontendToCategoryBackend(List<String> tags) {
        return tags.parallelStream().map(CategoryDomain::new).collect(Collectors.toList());
    }

    public static List<CategoryDomain> mapCategoryIdListFrontendToCategoryBackend(List<Long> tags) {
        return tags.parallelStream().map(CategoryDomain::new).collect(Collectors.toList());
    }
}