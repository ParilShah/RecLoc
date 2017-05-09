package csc258.bootstrap;

import csc258.dao.UserDao;
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

//            CategoryDomain forest = new CategoryDomain(1L, "Forest", new ArrayList<UserDomain>() {{
//                add(new UserDomain("1234"));
//                add(new UserDomain("1235"));
//            }});
//            CategoryDomain beach = new CategoryDomain(2L, "Beach", new ArrayList<UserDomain>() {{
//                add(new UserDomain("1234"));
//                add(new UserDomain("1236"));
//            }});
//            CategoryDomain mountain = new CategoryDomain(3L, "Mountain", new ArrayList<UserDomain>() {{
//                add(new UserDomain("1235"));
//                add(new UserDomain("1237"));
//            }});
            CategoryDomain forest = new CategoryDomain(1L, "Forest");
            CategoryDomain beach = new CategoryDomain(2L, "Beach");
            CategoryDomain mountain = new CategoryDomain(3L, "Mountain");
            CategoryDomain river = new CategoryDomain(4L, "River");
            CategoryDomain waterfall = new CategoryDomain(5L, "Waterfall");

            List<CategoryDomain> categoryDomainList1 = new ArrayList<CategoryDomain>() {{
                add(river);
                add(forest);
                add(beach);
                add(mountain);
                add(waterfall);
            }};
//            categoryService.getCategoryDao().saveAllCategories(categoryDomainList1);
            List<CategoryDomain> categoryDomainList2 = new ArrayList<CategoryDomain>() {{
                add(river);
                add(forest);
//                add(beach);
//                add(mountain);
//                add(disney);
            }};
            List<CategoryDomain> categoryDomainList3 = new ArrayList<CategoryDomain>() {{
                add(river);
//                add(forest);
//                add(beach);
//                add(mountain);
//                add(disney);
            }};
            List<CategoryDomain> categoryDomainList4 = new ArrayList<CategoryDomain>() {{
                add(river);
//                add(forest);
                add(beach);
                add(mountain);
//                add(disney);
            }};
            // save a couple of categories
//            categoryService.getCategoryDao().saveAllCategories(categoryDomainList1);
//            System.out.println(categoryService.findCategoryByCategoryId(1));

            UserDomain userDomain1 = new UserDomain("1234", categoryDomainList1);
            UserDomain userDomain2 = new UserDomain("1235", categoryDomainList2);
            UserDomain userDomain3 = new UserDomain("1236", categoryDomainList3);
            UserDomain userDomain4 = new UserDomain("1237", categoryDomainList4);
            UserDao userDao = userService.getUserDao();
            userDao.saveUser(userDomain1);
            userDao.saveUser(userDomain2);
            userDao.saveUser(userDomain3);
            userDao.saveUser(userDomain4);
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