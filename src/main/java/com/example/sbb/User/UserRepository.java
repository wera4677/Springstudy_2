package com.example.sbb.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//리포지터리 생성
public interface UserRepository extends JpaRepository<SiteUser,Long> {
    Optional<SiteUser> findByUsername(String username);
}
