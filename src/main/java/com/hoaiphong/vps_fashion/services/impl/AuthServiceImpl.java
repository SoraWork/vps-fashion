package com.hoaiphong.vps_fashion.services.impl;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoaiphong.vps_fashion.dtos.auth.ForgotPasswordDTO;
import com.hoaiphong.vps_fashion.dtos.auth.RegisterDTO;
import com.hoaiphong.vps_fashion.entities.User;
import com.hoaiphong.vps_fashion.repositories.AccountRepository;
import com.hoaiphong.vps_fashion.services.AuthService;
@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }


    @Override
    public UUID save(RegisterDTO registerDTO) {
        var existingUser = existsByUsername(registerDTO.getUsername());
        if (existingUser) {
            throw new IllegalArgumentException("Username already exists");
        }
        var existingEmail = existsByEmail(registerDTO.getEmail());
        if (existingEmail) {
            throw new IllegalArgumentException("Email already exists");
        }

        var user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        return accountRepository.save(user).getId();
    }

    @Override
    public boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = accountRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Trả về một tập hợp rỗng cho grantedAuthorities
        Set<GrantedAuthority> grantedAuthorities = Collections.emptySet();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthorities);
    }


   @Override
   public UUID save(ForgotPasswordDTO forgotPasswordDTO) {
       var user = accountRepository.findByUsername(forgotPasswordDTO.getUsername());
       if (user == null) {
           throw new UsernameNotFoundException("User not found");
       }
       user.setPassword(passwordEncoder.encode(forgotPasswordDTO.getPassword()));
       return accountRepository.save(user).getId();
   }

}
