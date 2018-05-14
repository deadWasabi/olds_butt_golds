package ru.tvstu.AccountingSystemHousingServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserCredentialModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserInfoModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserModel;
import ru.tvstu.AccountingSystemHousingServices.service.UserService;

@CrossOrigin
@RestController
public class UserContoller {

    @Autowired
    private UserService userService;

    private static final String  SIGN_UP_PATH = "/sign-up";
    private static final String USER_INFO_PATH = "/userInfo";

    @ResponseBody
    @RequestMapping(value = SIGN_UP_PATH, method = RequestMethod.POST)
    public WebUserModel signUp(@RequestBody WebUserCredentialModel webUserCredentialModel) {
        return userService.signUp(webUserCredentialModel);
    }

    @ResponseBody
    @RequestMapping(value = USER_INFO_PATH, method = RequestMethod.POST)
    public WebUserInfoModel setUserInfo(@RequestBody WebUserInfoModel webUserInfoModel) {
        return userService.setUserInfoByUserName(webUserInfoModel, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @ResponseBody
    @RequestMapping(value = USER_INFO_PATH, method = RequestMethod.GET)
    public WebUserInfoModel getUserInfo() {
        return userService.getUserInfoByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
