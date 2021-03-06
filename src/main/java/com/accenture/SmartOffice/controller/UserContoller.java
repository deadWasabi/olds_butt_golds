package com.accenture.SmartOffice.controller;

import com.accenture.SmartOffice.model.web.WebUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.accenture.SmartOffice.model.web.WebUserCredentialModel;
import com.accenture.SmartOffice.service.UserService;

@CrossOrigin
@RestController
public class UserContoller {

    @Autowired
    private UserService userService;

    private static final String SIGN_UP = "/sign-up";
    private static final String GET_USER_INFO = "/userInfo/{id}";
    private static final String SET_USER_INFO = "/setUserInfo";
    private static final String DELETE_USER = "/deleteUser/{id}";
    private static final String LOGIN = "/login";


    @ResponseBody
    @PostMapping(value = SIGN_UP)
    public WebUserModel signUp(@RequestBody WebUserCredentialModel webUserCredentialModel) {
        return userService.signUp(webUserCredentialModel);
    }

    @ResponseBody
    @PostMapping(value = LOGIN)
    public WebUserModel login(@RequestBody WebUserCredentialModel webUserCredentialModel) {
        return userService.login(webUserCredentialModel);
    }

    //@ResponseBody
    //@PostMapping(value = SET_USER_INFO)
    //public WebUserInfoModel setUserInfo(@RequestBody WebUserInfoModel webUserInfoModel) {
    //    return userService.setUserInfoByUserName(webUserInfoModel, SecurityContextHolder.getContext().getAuthentication().getName());
    //}

    @ResponseBody
    @GetMapping(value = GET_USER_INFO)
    public WebUserModel getUserInfoByLogin(@PathVariable Long id) {
        return userService.getUserInfoByUserId(id);
    }

    @ResponseBody
    @GetMapping(value = DELETE_USER)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
