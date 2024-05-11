package com.example.shop.user;

import com.example.shop.error.SignupEmailDuplicatedException;
import com.example.shop.error.SignupUsernameDuplicatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password, String address){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);
        user.setPassword(passwordEncoder.encode(password));

        try {
            siteUserRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (siteUserRepository.existsByUsername(username)) {
                throw new SignupUsernameDuplicatedException("이미 사용중인 username입니다.");
            } else {
                throw new SignupEmailDuplicatedException("이미 사용중인 email입니다");
            }
        }

        return user;
    }
}
