package com.dsa_edu_be.dsa_edu_be.Repository;

import com.dsa_edu_be.dsa_edu_be.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
}
