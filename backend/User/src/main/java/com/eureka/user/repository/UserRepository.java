package com.eureka.user.repository;

import com.eureka.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findTop1ByEmail(String email);
}
