package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.model.web.WebUserModel;
import com.accenture.SmartOffice.model.web.WebUserCredentialModel;
import com.accenture.SmartOffice.model.web.WebUserInfoModel;

public interface UserService {
    WebUserModel signUp(WebUserCredentialModel webRegisterUserModel);

    WebUserModel login(WebUserCredentialModel webRegisterUserModel);

    WebUserModel getUserInfoByUserId(Long userId);

    WebUserInfoModel setUserInfoByUserName(WebUserInfoModel webUserInfoModel, String username);

    void deleteUser(Long userId);

}
