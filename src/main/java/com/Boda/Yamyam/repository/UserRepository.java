package com.Boda.Yamyam.repository;

import com.Boda.Yamyam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserId(String userId);
}
