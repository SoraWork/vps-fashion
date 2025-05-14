package com.hoaiphong.vps_fashion.services;

import java.util.UUID;

import com.hoaiphong.vps_fashion.dtos.auth.ForgotPasswordDTO;
import com.hoaiphong.vps_fashion.dtos.auth.RegisterDTO;

public interface AuthService {
    UUID save(RegisterDTO registerDTO);

    UUID save(ForgotPasswordDTO forgotPasswordDTO);

    boolean existsByUsername(String username); 

    boolean existsByEmail(String email);
}
