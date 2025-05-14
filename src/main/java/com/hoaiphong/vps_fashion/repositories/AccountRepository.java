package com.hoaiphong.vps_fashion.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hoaiphong.vps_fashion.entities.User;
@Repository
public interface AccountRepository  extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User>{
    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameAndEmail(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
