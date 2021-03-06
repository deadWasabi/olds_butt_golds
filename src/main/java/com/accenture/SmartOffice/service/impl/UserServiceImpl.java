package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.customException.ObjectNotFoundExeption;
import com.accenture.SmartOffice.customException.UserAlreadyExistsException;
import com.accenture.SmartOffice.dao.entity.User;
import com.accenture.SmartOffice.dao.repository.UserRepository;
import com.accenture.SmartOffice.model.web.WebUserModel;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.accenture.SmartOffice.model.web.WebUserCredentialModel;
import com.accenture.SmartOffice.model.web.WebUserInfoModel;
import com.accenture.SmartOffice.service.UserService;

import static java.util.Collections.emptyList;

@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public WebUserModel signUp(WebUserCredentialModel webRegisterUserModel) {
        if (!StringUtils.hasText(webRegisterUserModel.getUsername())) {
            throw new IllegalArgumentException("Incorrect user username!");
        }

        if (!StringUtils.hasText(webRegisterUserModel.getPassword())) {
            throw new IllegalArgumentException("Incorrect user password!");
        }

        if (userRepository.findByLoginAndHashPassword(webRegisterUserModel.getUsername(), webRegisterUserModel.getPassword()) == null) {
            User newUser = new User();
            newUser.setLogin(webRegisterUserModel.getUsername());
            //newUser.setHashPassword(bCryptPasswordEncoder.encode(webRegisterUserModel.getPassword()));
            newUser.setEmail(webRegisterUserModel.getEmail());
            WebUserModel webUserModel = new WebUserModel();
            webUserModel.setUsername(newUser.getLogin());
            webUserModel.setEmail(newUser.getEmail());
            return webUserModel;
        } else {
            throw new UserAlreadyExistsException("User already registered!");
        }
    }

    @Override
    public WebUserModel login(WebUserCredentialModel webRegisterUserModel) {
        if (!StringUtils.hasText(webRegisterUserModel.getUsername()) || !StringUtils.hasText(webRegisterUserModel.getPassword())) {
            throw new IllegalArgumentException("Incorrect username or password!");
        }
        User user = userRepository.findByLoginAndHashPassword(webRegisterUserModel.getUsername(), webRegisterUserModel.getPassword());
        if (user == null) {
            throw new IllegalArgumentException("Incorrect username or password!");
        }
        WebUserModel webUserModel = new WebUserModel();
        mapper.map(user, webUserModel);
        return webUserModel;
    }

    @Override
    public WebUserModel getUserInfoByUserId(Long id) {
        return convertUserInfoToWebUserInfoModel(userRepository.findById(id));
    }

    @Override
    public WebUserInfoModel setUserInfoByUserName(WebUserInfoModel webUserInfoModel, String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    private WebUserModel convertUserInfoToWebUserInfoModel(User user) {
        WebUserModel webUserModel = new WebUserModel();
        webUserModel.setFirstName(user.getFirstName());
        webUserModel.setLastName(user.getLastName());
        webUserModel.setMiddleName(user.getMiddleName());
        webUserModel.setUsername(user.getLogin());
        webUserModel.setEmail(user.getEmail());
        webUserModel.setLevel(user.getCurrentLevel());
        webUserModel.setMobileNumber(user.getMobileNumber());
        return webUserModel;
    }

    private User convertWebUserInfoModelToUserInfo(WebUserInfoModel webUserInfoModel) {
        throw new UnsupportedOperationException();
    }

}