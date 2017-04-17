package csc258.bootstrap;

import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.user.UserDomain;
import csc258.service.category.CategoryService;
import csc258.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desair4 on 4/16/2017.
 */
@Component
public class Bootstrap {

    private CategoryService categoryService;

    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    public void init() {
        try {
            userService.saveUser("1234", null);
            userService.saveUser("1235", null);
            userService.saveUser("1236", null);
            userService.saveUser("1237", null);

            CategoryDomain forest = new CategoryDomain(1L, "Forest", new ArrayList<UserDomain>() {{
                add(new UserDomain("1234"));
                add(new UserDomain("1235"));
            }});
            CategoryDomain beach = new CategoryDomain(2L, "Beach", new ArrayList<UserDomain>() {{
                add(new UserDomain("1234"));
                add(new UserDomain("1236"));
            }});
            CategoryDomain mountain = new CategoryDomain(3L, "Mountain", new ArrayList<UserDomain>() {{
                add(new UserDomain("1235"));
                add(new UserDomain("1237"));
            }});
            CategoryDomain disney = new CategoryDomain(4L, "Disney");
            CategoryDomain india = new CategoryDomain(5L, "India");
            List<CategoryDomain> categoryDomainList = new ArrayList<CategoryDomain>() {{
                add(india);
                add(forest);
                add(beach);
                add(mountain);
                add(disney);
            }};
            // save a couple of categories
            categoryService.getCategoryDao().saveAllCategories(categoryDomainList);
            System.out.println(categoryService.findCategoryByCategoryId(1));

            /*categoryService.save(new CategoryDomain(1L, "Forest", new ArrayList<UserDomain>() {{
                add(new UserDomain("1234"));
                add(new UserDomain("1235"));
            }}));
            categoryService.save(new CategoryDomain(2L, "Beach", new ArrayList<UserDomain>() {{
                add(new UserDomain("1234"));
                add(new UserDomain("1236"));
            }}));
            categoryService.save(new CategoryDomain(3L, "Mountain", new ArrayList<UserDomain>() {{
                add(new UserDomain("1235"));
                add(new UserDomain("1237"));
            }}));
            categoryService.save(new CategoryDomain(4L, "Disney"));
            categoryService.save(new CategoryDomain(5L, "India"));*/


            // fetch all categories
            LOG.info("Categories found with findAll():");
            LOG.info("-------------------------------");
            /*for (CategoryDomain categoryDomain : categoryService()) {
                LOG.info(categoryDomain.toString());
            }*/


            LOG.info("");
        } catch (Exception e) {
            e.printStackTrace();
        }
            /*// fetch an individual category by ID
            CategoryDomain category = repository.findOne(1L);
            LOG.info("CategoryDomain found with findOne(1L):");
            LOG.info("--------------------------------");
            LOG.info(category.toString());
            LOG.info("");*/

        // fetch categories by categoryName
            /*LOG.info("CategoryDomain found with findByCategoryName('Forest'):");
            LOG.info("--------------------------------------------");
            for (CategoryDomain forest : repository.findByCategoryName("forest")) {
                LOG.info(forest.toString());
            }
            LOG.info("");*/
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}