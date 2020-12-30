package com.things.project02.repository.user;

import com.things.project02.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 로그인 정보(아이디, 비밀번호) 조회
    Optional<User> findByUserIdAndUserPw(String userId, String userPw);
}