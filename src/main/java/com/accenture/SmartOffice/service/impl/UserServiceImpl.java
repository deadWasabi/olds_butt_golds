package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.customException.ObjectNotFoundExeption;
import com.accenture.SmartOffice.customException.UserAlreadyExistsException;
import com.accenture.SmartOffice.dao.entity.User;
import com.accenture.SmartOffice.dao.repository.UserRepository;
import com.accenture.SmartOffice.model.web.WebUserModel;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.accenture.SmartOffice.model.web.WebUserCredentialModel;
import com.accenture.SmartOffice.model.web.WebUserInfoModel;
import com.accenture.SmartOffice.service.UserService;

import static java.util.Collections.emptyList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

        if (userRepository.findByUserName(webRegisterUserModel.getUsername()) == null) {
            User newUser = new User();
            newUser.setLogin(webRegisterUserModel.getUsername());
            newUser.setHashPassword(bCryptPasswordEncoder.encode(webRegisterUserModel.getPassword()));
            newUser.setEmail(webRegisterUserModel.getEmail());
            newUser = userRepository.save(newUser);
            WebUserModel webUserModel = new WebUserModel();
            webUserModel.setUsername(newUser.getLogin());
            webUserModel.setEmail(newUser.getEmail());
            return webUserModel;
        } else {
            throw new UserAlreadyExistsException("User already registered!");
        }
    }

    @Override
    public WebUserInfoModel getUserInfoByUserName(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WebUserInfoModel setUserInfoByUserName(WebUserInfoModel webUserInfoModel, String username) {
        throw new UnsupportedOperationException();
    }

    private WebUserInfoModel convertUserInfoToWebUserInfoModel(User user) {
        throw new UnsupportedOperationException();
    }

    private User convertWebUserInfoModelToUserInfo(WebUserInfoModel webUserInfoModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userName  == null) {
            throw new ObjectNotFoundExeption("User not found");
        }
        return userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUser(Long userId){
        //TODO
    }
}