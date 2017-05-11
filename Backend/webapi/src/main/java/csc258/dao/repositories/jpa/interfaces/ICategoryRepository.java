package csc258.dao.repositories.jpa.interfaces;

import csc258.domain.db.category.CategoryDomain;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Paril on 4/15/2017.
 */
public interface ICategoryRepository extends CrudRepository<CategoryDomain, Long> {
    CategoryDomain findByCategoryId(Long id1);

    List<CategoryDomain> findByCategoryName(String categoryName1);

    List<CategoryDomain> findByCategoryIdAndCategoryName(Long id1, String categoryName1);

    List<CategoryDomain> findByCategoryIdOrCategoryName(Long id1, String categoryName1);

    Collection<CategoryDomain> findByCategoryIdIsIn(Collection<Long> categoryId);

    Collection<CategoryDomain> findByCategoryNameIn(Collection<String> categoryNames);

//    void saveAll(List<CategoryDomain> categoryDomain);
}