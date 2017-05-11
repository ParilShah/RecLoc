package csc258.mappers.user;

import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.location.LocationDomain;
import csc258.domain.db.user.UserDomain;
import csc258.domain.frontend.user.User;
import csc258.mappers.category.CategoryMapper;
import csc258.mappers.location.LocationMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Paril on 4/24/2017.
 */
public class UserMapper {
    public static User mapUserBackendToFrontend(UserDomain userDomain) {
        if (userDomain == null) return null;

        List<CategoryDomain> categoryDomains = userDomain.getCategoryDomains();
        List<LocationDomain> locationDomains = userDomain.getLocationDomains();
        return new User(userDomain.getDeviceId(),
                CategoryMapper.mapCategoryListBackendToFrontend(categoryDomains),
                LocationMapper.mapLocationListBackendToFrontend(locationDomains));
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
        return new UserDomain(user.getDeviceId(),
                CategoryMapper.mapCategoryListFrontendToBackend(user.getCategory()),
                LocationMapper.mapLocationListFrontendToBackend(user.getLocations()));
    }
}