package csc258.mappers.user;

import csc258.domain.db.user.UserDomain;
import csc258.domain.frontend.user.User;
import csc258.mappers.category.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by desair4 on 4/24/2017.
 */
public class UserMapper {
    public static User mapUserBackendToFrontend(UserDomain userDomain) {
        if (userDomain == null) return null;
        return new User(userDomain.getDeviceId(), CategoryMapper.mapCategoryListBackendToFrontend(userDomain.getCategoryDomains()));
    }

    public static List<User> mapUserListBackendToFrontend(List<UserDomain> userDomainList) {
        if (userDomainList == null) return null;
        return userDomainList.parallelStream().map(UserMapper::mapUserBackendToFrontend).collect(Collectors.toList());
    }

    public static List<UserDomain> mapUserListFrontendToBackend(List<User> categoryList) {
        if (categoryList == null) return null;
        return categoryList.parallelStream().map(UserMapper::mapUserFrontendToBackend).collect(Collectors.toList());
    }

    public static UserDomain mapUserFrontendToBackend(User user) {
        if (user == null) return null;
        return new UserDomain(user.getDeviceId(), CategoryMapper.mapCategoryListFrontendToBackend(user.getCategory()));
    }
}