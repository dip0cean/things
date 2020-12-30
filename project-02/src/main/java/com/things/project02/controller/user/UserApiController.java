package com.things.project02.controller.user;

import com.things.project02.dto.UserDto;
import com.things.project02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/api/user/signup")
    public ResponseEntity<Long> signup(@RequestBody UserDto.UserReq userReq) {
        UserDto.UserRes userRes = userService.signup(userReq);
        return ResponseEntity.ok(userRes.getId());
    }

    // 로그인
    @PostMapping("/api/user/signin")
    public ResponseEntity<Boolean> signIn(@RequestBody UserDto.UserReq userReq, HttpSession session) {
        UserDto.UserRes userRes = userService.signIn(userReq);

        if(userRes != null) {
            session.setAttribute("userSession",userRes);
        }
        return ResponseEntity.ok(userRes != null);
    }

    // 비밀번호 체크
    @PostMapping("/api/user/check")
    public ResponseEntity<Boolean> checkPw(@RequestBody UserDto.UserReq userReq) {
        return ResponseEntity.ok(userService.checkPw(userReq));
    }

    // 회원 정보 수정
    @PatchMapping("/api/user/update")
    public ResponseEntity userUpdate(@RequestBody UserDto.UserReq userReq) {
        userService.userUpdate(userReq);
        return ResponseEntity.ok().build();
    }

    //  회원 탈퇴
    @DeleteMapping("/api/user/delete")
    public ResponseEntity userDelete(@RequestBody UserDto.UserReq userReq) {
        userService.userDelete(userReq);
        return ResponseEntity.ok().build();
    }
}
