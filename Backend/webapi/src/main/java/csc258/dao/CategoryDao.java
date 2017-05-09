package csc258.dao;

import csc258.dao.repositories.jpa.interfaces.ICategoryRepository;
import csc258.domain.db.category.CategoryDomain;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by desair4 on 4/15/2017.
 */
@Component
@Transactional
public class CategoryDao {

    private ICategoryRepository categoryRepository;

    public List<CategoryDomain> fetchAllCategories() {
        Iterable<CategoryDomain> categoryDomainIterable = categoryRepository.findAll();
        List<CategoryDomain> categoryDomainList = new ArrayList<>();
        CollectionUtils.addAll(categoryDomainList, categoryDomainIterable);
        return categoryDomainList;
    }

    public CategoryDomain findCategoryByCategoryId(long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    public List<CategoryDomain> findCategoryByCategoriesByIds(List<CategoryDomain> categoryDomainList) {
        final List<Long> list = categoryDomainList.parallelStream().map(CategoryDomain::getCategoryId).collect(Collectors.toList());
        return categoryRepository.findByCategoryIdIsIn(list).parallelStream().collect(Collectors.toList());
    }

    public List<CategoryDomain> findByCategoryNameIn(List<String> categoryNamesList) {
        return categoryRepository.findByCategoryNameIn(categoryNamesList).parallelStream().collect(Collectors.toList());
    }

    public CategoryDomain saveCategory(CategoryDomain categoryDomain) {
        return categoryRepository.save(categoryDomain);
    }

//    public void saveAllCategories(List<CategoryDomain> categoryDomain) {
////        categoryRepository.save(categoryDomain);
//    }

    public ICategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    @Autowired
    public void setCategoryRepository(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    void afterPropertiesSet() {
        Assert.notNull(categoryRepository, "categoryRepository cannot be null");
    }

}