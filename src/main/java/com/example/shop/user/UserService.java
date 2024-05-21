package com.example.shop.user;

import com.example.shop.cart.Cart;
import com.example.shop.error.DataNotFoundException;
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

    public SiteUser createUser(String username, String email, String password, String address){

        SiteUser user = SiteUser.builder()
                        .username(username)
                        .email(email)
                        .address(address)
                        .password(passwordEncoder.encode(password))
                        .cart(new Cart())
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
        userRepository.save(userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("no such data"))
                .addCashEntity(add));
    }

    public void changeUserBasicInfo(String username, String password, String email, String address){
        userRepository.save(userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("no such data"))
                .changeUserBasicInfoEntity(passwordEncoder.encode(password), email, address));
    }

    public SiteUser getUser(String username) {

        return userRepository.findByUsername(username).orElseThrow(() ->
                new DataNotFoundException("siteuser no found"));
    }

    public SiteUser getUser(long id) {

        return userRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("siteuser no found"));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
