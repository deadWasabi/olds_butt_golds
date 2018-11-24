package com.accenture.SmartOffice.controller;

import com.accenture.SmartOffice.model.web.WebUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.accenture.SmartOffice.model.web.WebUserCredentialModel;
import com.accenture.SmartOffice.model.web.WebUserInfoModel;
import com.accenture.SmartOffice.service.UserService;

@CrossOrigin
@RestController
public class UserContoller {

    @Autowired
    private UserService userService;

    private static final String SIGN_UP = "/sign-up";
    private static final String GET_USER_INFO = "/userInfo";
    private static final String SET_USER_INFO = "/setUserInfo";
    private static final String DELETE_USER = "/deleteUser";

    @ResponseBody
    @PostMapping(value = SIGN_UP)
    public WebUserModel signUp(@RequestBody WebUserCredentialModel webUserCredentialModel) {
        return userService.signUp(webUserCredentialModel);
    }

    @ResponseBody
    @PostMapping(value = SET_USER_INFO)
    public WebUserInfoModel setUserInfo(@RequestBody WebUserInfoModel webUserInfoModel) {
        return userService.setUserInfoByUserName(webUserInfoModel, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @ResponseBody
    @GetMapping(value = GET_USER_INFO)
    public WebUserInfoModel getUserInfo() {
        return userService.getUserInfoByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @ResponseBody
    @GetMapping(value = DELETE_USER)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
