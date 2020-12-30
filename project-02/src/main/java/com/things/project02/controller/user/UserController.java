package com.things.project02.controller.user;

import com.things.project02.dto.UserDto;
import com.things.project02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인
    @GetMapping("/")
    public String signIn() {
        return "signin";
    }

    // 회원가입
    @GetMapping("/user/signup")
    public String signUp() {
        return "signup";
    }

    // 로그아웃
    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.removeAttribute("userSession");
        return "redirect:/";
    }

    // 회원 상세 페이지
    @GetMapping("/user/detail/{id}")
    public String userDetail(@PathVariable Long id, Model model) {
        UserDto.UserRes userRes = userService.findById(id);
        model.addAttribute("user", userRes);
        return "user/detail";
    }

    // 회원 수정 페이지
    @GetMapping("/user/update/{id}")
    public String userUpdate(@PathVariable Long id, Model model) {
        UserDto.UserRes userRes = userService.findById(id);
        model.addAttribute("user",userRes);
        return "user/update";
    }
}
