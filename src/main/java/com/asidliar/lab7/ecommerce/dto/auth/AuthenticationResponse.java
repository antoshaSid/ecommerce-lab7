package com.asidliar.lab7.ecommerce.dto.auth;

import com.asidliar.lab7.ecommerce.dto.user.UserResponse;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private UserResponse user;
    private String token;
}
