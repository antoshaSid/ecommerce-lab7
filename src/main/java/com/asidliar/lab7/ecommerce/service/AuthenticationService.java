package com.asidliar.lab7.ecommerce.service;

import com.asidliar.lab7.ecommerce.security.oauth2.OAuth2UserInfo;
import com.asidliar.lab7.ecommerce.domain.User;

import java.util.Map;

public interface AuthenticationService {

    Map<String, Object> login(String email, String password);

    String registerUser(User user, String captcha, String password2);

    User registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo);

    User updateOauth2User(User user, String provider, OAuth2UserInfo oAuth2UserInfo);

    String activateUser(String code);

    String getEmailByPasswordResetCode(String code);

    String sendPasswordResetCode(String email);

    String passwordReset(String email, String password, String password2);
}
