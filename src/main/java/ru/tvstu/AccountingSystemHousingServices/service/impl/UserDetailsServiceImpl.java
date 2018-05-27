package ru.tvstu.AccountingSystemHousingServices.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.tvstu.AccountingSystemHousingServices.customException.ObjectNotFoundExeption;
import ru.tvstu.AccountingSystemHousingServices.customException.UserAlreadyExistsException;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.User;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.UserInfo;
import ru.tvstu.AccountingSystemHousingServices.dao.repository.UserInfoRepository;
import ru.tvstu.AccountingSystemHousingServices.dao.repository.UserRepository;
import ru.tvstu.AccountingSystemHousingServices.model.IdentityDocument;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserCredentialModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserInfoModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebUserModel;
import ru.tvstu.AccountingSystemHousingServices.service.UserService;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getHashPassword(), emptyList());
    }


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
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Incorrect user username!");
        }

        User user = userRepository.findByUserName(username);
        UserInfo userInfo = user.getUserInfo();
        if (userInfo != null) {
            return convertUserInfoToWebUserInfoModel(userInfo);
        }
        return null;
    }

    @Override
    public WebUserInfoModel setUserInfoByUserName(WebUserInfoModel webUserInfoModel, String username) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Incorrect user username!");
        }

        User user = userRepository.findByUserName(username);
        UserInfo userInfo = convertWebUserInfoModelToUserInfo(webUserInfoModel);
        userInfo.setUser(user);
        if(user.getUserInfo() != null) {
            userInfo.setId(user.getUserInfo().getId());
        }
        userInfo = userInfoRepository.save(userInfo);
        return convertUserInfoToWebUserInfoModel(userInfo);
    }

    private WebUserInfoModel convertUserInfoToWebUserInfoModel(UserInfo userInfo) {
        WebUserInfoModel webUserInfoModel = new WebUserInfoModel();
        webUserInfoModel.setFirstName(userInfo.getFirstName());
        webUserInfoModel.setLastName(userInfo.getLastName());
        webUserInfoModel.setMiddleName(userInfo.getMiddleName());

        IdentityDocument identityDocument = new IdentityDocument();
        identityDocument.setDocumentType(userInfo.getDocumentType());
        identityDocument.setNumber(userInfo.getNumber());
        identityDocument.setSerial(userInfo.getSerial());
        identityDocument.setIssueDate(userInfo.getIssueDate());
        identityDocument.setIssuerCode(userInfo.getIssuerCode());
        identityDocument.setIssuer(userInfo.getIssuer());
        webUserInfoModel.setIdentityDocument(identityDocument);
        return webUserInfoModel;
    }

    private UserInfo convertWebUserInfoModelToUserInfo(WebUserInfoModel webUserInfoModel) {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(webUserInfoModel.getFirstName());
        userInfo.setLastName(webUserInfoModel.getLastName());
        userInfo.setMiddleName(webUserInfoModel.getMiddleName());
        if (webUserInfoModel.getIdentityDocument() != null) {
            userInfo.setDocumentType(webUserInfoModel.getIdentityDocument().getDocumentType());
            userInfo.setNumber(webUserInfoModel.getIdentityDocument().getNumber());
            userInfo.setSerial(webUserInfoModel.getIdentityDocument().getSerial());
            userInfo.setIssueDate(webUserInfoModel.getIdentityDocument().getIssueDate());
            userInfo.setIssuer(webUserInfoModel.getIdentityDocument().getIssuer());
            userInfo.setIssuerCode(webUserInfoModel.getIdentityDocument().getIssuerCode());
        }
        return userInfo;
    }

    @Override
    public User getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userName  == null) {
            throw new  ObjectNotFoundExeption("User not found");
        }
        return userRepository.findByUserName(userName);
    }
}