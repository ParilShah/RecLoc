package csc258.service.category;

import csc258.dao.CategoryDao;
import csc258.domain.db.category.CategoryDomain;
import csc258.domain.frontend.category.Category;
import csc258.domain.frontend.category.fetchcategory.FetchCategoryResponse;
import csc258.domain.frontend.common.ResponseDetail;
import csc258.mappers.category.CategoryMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by desair4 on 4/16/2017.
 */
@Service
public class CategoryService {

    private CategoryDao categoryDao;

//    public void saveAllCategories(List<Category> category) {
//        List<CategoryDomain> categoryDomain = CategoryMapper.mapCategoryListFrontendToBackend(category);
//        categoryDao.saveAllCategories(categoryDomain);
//    }

    public FetchCategoryResponse fetchAllCategories() {
        Iterable<CategoryDomain> categoryDomainIterable = categoryDao.fetchAllCategories();
        List<CategoryDomain> categoryDomainList = new ArrayList<>();
        CollectionUtils.addAll(categoryDomainList, categoryDomainIterable);
        List<Category> categoryList = CategoryMapper.mapCategoryListBackendToFrontend(categoryDomainList);
        return new FetchCategoryResponse(new ResponseDetail(200L, "Fetch All Categories Success"), categoryList);
    }

    public boolean saveCategory(Category category) {
        try {
            categoryDao.saveCategory(CategoryMapper.mapCategoryFrontendToBackend(category));
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    public Category findCategoryByCategoryId(long categoryId) {
        return CategoryMapper.mapCategoryBackendToFrontend(categoryDao.findCategoryByCategoryId(categoryId));
    }

    public List<Category> findCategoriesByCategoryIds(List<Category> categoryList) {
        return CategoryMapper
                .mapCategoryListBackendToFrontend((
                        categoryDao.findCategoryByCategoriesByIds(
                                CategoryMapper.mapCategoryListFrontendToBackend(categoryList)
                        )));
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @PostConstruct
    void afterPropertiesSet() {
        Assert.notNull(categoryDao, "categoryDao cannot be null");
    }
}
//Controller ->
//      Service -> Use Mapper (Frontend(json) to Backend(DB) or Backend to Frontend)
//

//input -> frontend
//output -> frontend output
/*
    Category findByCategoryId(Long id1) {
        CategoryDomain categoryDomain = categoryDao.categoryRepository.findByCategoryId(id1);   //conect to db1
        //connect to webservice1
        //connect to webservice2

        return CategoryMapper.mapCategoryBackendToFrontend(categoryDomain);
    }
*/


/*
    public ResponseDetail submitCategory(SubmitUserRequest submitCategoryRequest) {

        //update category table
        //update JoinTable

        return null;
    }
*/

    /*List<Category> findByCategoryName(String categoryName1) {
        return null;
    }

    List<Category> findByCategoryIdAndCategoryName(Long id1, String categoryName1) {
        return null;
    }

    List<Category> findByCategoryIdOrCategoryName(Long id1, String categoryName1) {
        return null;
    }*/
