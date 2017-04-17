package csc258.controllers;

import csc258.domain.frontend.common.ResponseDetail;
import csc258.domain.frontend.submituser.SubmitUserRequest;
import csc258.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by desair4 on 4/16/2017.
 */
@RestController
public class UserController {

    private UserService userService;

    @RequestMapping(value = "/submitUser", method = RequestMethod.POST)
    public ResponseDetail category(@RequestBody SubmitUserRequest submitUserRequest, HttpServletRequest request, HttpServletResponse response) {
        ResponseDetail responseDetail = userService.saveUser(submitUserRequest.getDeviceId(), submitUserRequest.getCategory());

        if (responseDetail.getResponseCode() != 200) {
            responseDetail.setResponseMessage("Submit User Failed");
        } else {
            responseDetail.setResponseMessage("Submit User successful");
        }

        return responseDetail;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    void afterPropertiesSet() {
        Assert.notNull(userService, "userService cannot be null");
    }
}