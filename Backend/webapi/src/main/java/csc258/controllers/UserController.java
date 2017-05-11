package csc258.controllers;

import csc258.domain.frontend.category.Category;
import csc258.domain.frontend.common.ResponseDetail;
import csc258.domain.frontend.location.Location;
import csc258.domain.frontend.user.User;
import csc258.domain.frontend.user.submituser.SubmitUserRequest;
import csc258.exceptions.SaveUserFailedException;
import csc258.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Paril on 4/16/2017.
 */
@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
//    private CategoryService categoryService;

    @RequestMapping(value = "/submitUser", method = RequestMethod.POST)
    public ResponseDetail category(@RequestBody SubmitUserRequest submitUserRequest, HttpServletRequest request, HttpServletResponse response) {
        ResponseDetail responseDetail = null;
        try {
            /*if (submitUserRequest.getCategory() != null) {
                categoryService.saveAllCategories(submitUserRequest.getCategory());
            }*/
            responseDetail = userService.saveUser(submitUserRequest.getDeviceId(), submitUserRequest.getCategory());
        } catch (SaveUserFailedException e) {
            return new ResponseDetail(401L);
        }

        if (responseDetail.getResponseCode() != 200) {
            responseDetail.setResponseMessage("Submit User Failed");
        } else {
            responseDetail.setResponseMessage("Submit User successful");
        }

        return responseDetail;
    }

    @RequestMapping(value = "/fetchAllUsers", method = RequestMethod.GET)
    public List<User> fetchAllUsers(HttpServletRequest request, HttpServletResponse response) {
        final List<User> userList = userService.fetchAllUsers();
        return userList;
    }

    @RequestMapping(value = "/fetchUserCategories/{deviceId}", method = RequestMethod.GET)
    public List<Category> fetchUserCategories(@PathVariable String deviceId, HttpServletRequest request, HttpServletResponse response) {
        final User user = userService.fetchUser(new User(deviceId));
        return user.getCategory();
    }

    @RequestMapping(value = "/fetchUserLocations/{deviceId}", method = RequestMethod.GET)
    public List<Location> fetchUserLocations(@PathVariable String deviceId) {
        final User user = userService.fetchUser(new User(deviceId));
        return user.getLocations();
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    public CategoryService getCategoryService() {
//        return categoryService;
//    }
//
//    @Autowired
//    public void setCategoryService(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @PostConstruct
    void afterPropertiesSet() {
        Assert.notNull(userService, "userService cannot be null");
    }
}