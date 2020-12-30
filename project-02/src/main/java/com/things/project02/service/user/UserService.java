package com.things.project02.service.user;

import com.things.project02.domain.User;
import com.things.project02.dto.UserDto;
import com.things.project02.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    public UserDto.UserRes signup(UserDto.UserReq userReq) {
        return new UserDto.UserRes(userRepository.save(userReq.toEntity()));
    }

    // 단일 조회
    public UserDto.UserRes findById(Long id) {
        return userRepository.findById(id).map(UserDto.UserRes::new).orElse(null);
    }

    // 로그인
    public UserDto.UserRes signIn(UserDto.UserReq userReq) {
        Optional<User> user = userRepository.findByUserIdAndUserPw(userReq.getUserId(), userReq.getUserPw());
        return user.map(UserDto.UserRes::new).orElse(null);
    }

    // 비밀번호 확인
    public boolean checkPw(UserDto.UserReq userReq) {
        UserDto.UserRes userRes = findById(userReq.getId());
        return userRes.getUserPw().equals(userReq.getUserPw());
    }

    // 회원 정보 수정
    public void userUpdate(UserDto.UserReq userReq) {
        userRepository.findById(userReq.getId()).ifPresent(user -> user.update(userReq.toEntity()));
    }

    // 회원 탈퇴
    public void userDelete(UserDto.UserReq userReq) {
        userRepository.delete(userReq.toEntity());
    }
}
