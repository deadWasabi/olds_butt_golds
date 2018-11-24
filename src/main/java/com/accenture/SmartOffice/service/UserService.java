package com.accenture.SmartOffice.service;

import com.accenture.SmartOffice.dao.entity.User;
import com.accenture.SmartOffice.model.web.WebUserModel;
import com.accenture.SmartOffice.model.web.WebUserCredentialModel;
import com.accenture.SmartOffice.model.web.WebUserInfoModel;

public interface UserService {
    WebUserModel signUp(WebUserCredentialModel webRegisterUserModel);

    WebUserInfoModel getUserInfoByUserName(String username);

    WebUserInfoModel setUserInfoByUserName(WebUserInfoModel webUserInfoModel, String username);

    User getCurrentUser();

    void deleteUser(Long userId);

}
