package com.things.project02.controller.post;

import com.things.project02.dto.PostDto;
import com.things.project02.dto.UserDto;
import com.things.project02.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/api/post/create")
    public ResponseEntity<Long> createPost(@RequestBody PostDto.PostReq postReq, HttpSession session) {
        UserDto.UserRes userRes = (UserDto.UserRes) session.getAttribute("userSession");
        Long            postId  = postService.createPost(postReq, userRes);
        return ResponseEntity.ok(postId);
    }

    // 게시글 수정
    @PatchMapping("/api/post/update")
    public ResponseEntity<Long> updatePost(@RequestBody PostDto.PostReq postReq, HttpSession session) {
        UserDto.UserRes userRes = (UserDto.UserRes) session.getAttribute("userSession");
        postService.updatePost(postReq, userRes);
        return ResponseEntity.ok(postReq.getId());
    }

    // 게시글 삭제
    @DeleteMapping("/api/post/delete")
    public ResponseEntity deletePost(@RequestBody PostDto.PostReq postReq) {
        postService.deletePost(postReq);
        return ResponseEntity.ok().build();
    }
}
