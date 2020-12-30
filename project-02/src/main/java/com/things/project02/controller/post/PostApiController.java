package com.things.project02.controller.post;

import com.things.project02.dto.PostDto;
import com.things.project02.dto.UserDto;
import com.things.project02.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/api/post/create")
    public ResponseEntity<Long> create(@RequestBody PostDto.PostReq postReq, HttpSession session) {
        UserDto.UserRes userRes = (UserDto.UserRes) session.getAttribute("userSession");
        Long postId = postService.create(postReq, userRes);
        return ResponseEntity.ok(postId);
    }
}
