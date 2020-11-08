package com.secondlife.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    @Query(value = "SELECT p FROM UserProfile p WHERE p.userName=:username")
    UserProfile getByUsername(@Param("username") String username);
}
