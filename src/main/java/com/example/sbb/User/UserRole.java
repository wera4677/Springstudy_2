package com.example.sbb.User;

import lombok.Getter;

@Getter
public enum UserRole { //권한 부분
    ADMIN("ROLE_ADMIN"), //관리자가 다른사용자 작성한것을 수정 가능 권한
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
