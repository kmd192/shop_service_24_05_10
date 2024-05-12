package com.example.shop.user;

import com.example.shop.error.SignupEmailDuplicatedException;
import com.example.shop.error.SignupUsernameDuplicatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password, String address){
        SiteUser user = SiteUser.builder()
                        .username(username)
                        .email(email)
                        .address(address)
                        .password(passwordEncoder.encode(password))
                        .build();

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (userRepository.existsByUsername(username)) {
                throw new SignupUsernameDuplicatedException("이미 사용중인 username입니다.");
            } else {
                throw new SignupEmailDuplicatedException("이미 사용중인 email입니다");
            }
        }

        return user;
    }

    public void addCash(String username, double add){
        SiteUser user = userRepository.findByUsername(username).get().addCashEntity(add);
        userRepository.save(user);
    }

    public void changeUserBasicInfo(String username, String password, String email, String address){
        SiteUser user = userRepository.findByUsername(username).get().changeUserBasicInfoEntity(passwordEncoder.encode(password), email, address);
        userRepository.save(user);
    }
}
