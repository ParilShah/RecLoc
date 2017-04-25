package csc258.service.user;

import csc258.dao.UserDao;
import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.user.UserDomain;
import csc258.domain.frontend.category.Category;
import csc258.domain.frontend.common.ResponseDetail;
import csc258.domain.frontend.user.User;
import csc258.exceptions.SaveUserFailedException;
import csc258.mappers.category.CategoryMapper;
import csc258.mappers.user.UserMapper;
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
public class UserService {

    private UserDao userDao;

    public ResponseDetail saveUser(String deviceId, List<Category> categoryList) throws SaveUserFailedException {
        List<CategoryDomain> categoryDomains = CategoryMapper.mapCategoryListFrontendToBackend(categoryList);
        UserDomain userDomain = new UserDomain(deviceId, categoryDomains);
        try {
            userDao.saveUser(userDomain);
        } catch (Exception e) {
            throw new SaveUserFailedException("Save user failed", e);
        }
        return new ResponseDetail(200L);
    }

    public List<User> fetchAllUsers() {
//        try {
        Iterable<UserDomain> userDomainIterable = userDao.findAllUsers();
        List<UserDomain> userDomainList = new ArrayList<>();
        CollectionUtils.addAll(userDomainList, userDomainIterable);
        return UserMapper.mapUserListBackendToFrontend(userDomainList);
//        } catch (Exception e) {
//            return new ResponseDetail(401);
//        }
//        return new ResponseDetail(200);
    }

    public User fetchUser(User user) {
        return UserMapper
                .mapUserBackendToFrontend(userDao
                        .findUserById(UserMapper
                                .mapUserFrontendToBackend(user)));
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    void afterPropertiesSet() {
        Assert.notNull(userDao, "userDao cannot be null");
    }
}