package com.example.sbb.User;

import org.springframework.data.jpa.repository.JpaRepository;

//리포지터리 생성
public interface UserRepository extends JpaRepository<SiteUser,Long> {
}
