package com.asidliar.lab7.ecommerce.security;

import com.asidliar.lab7.ecommerce.constants.ErrorMessage;
import com.asidliar.lab7.ecommerce.repository.UserRepository;
import com.asidliar.lab7.ecommerce.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (user.getActivationCode() != null) {
            throw new LockedException("Email not activated");
        }
        return UserPrincipal.create(user);
    }
}
