package ru.tvstu.AccountingSystemHousingServices.service;

import ru.tvstu.AccountingSystemHousingServices.dao.entity.User;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserCredentialModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserInfoModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserModel;

public interface UserService {
    WebUserModel signUp(WebUserCredentialModel webRegisterUserModel);

    WebUserInfoModel getUserInfoByUserName(String username);

    WebUserInfoModel setUserInfoByUserName(WebUserInfoModel webUserInfoModel, String username);

    User getCurrentUser();
}
