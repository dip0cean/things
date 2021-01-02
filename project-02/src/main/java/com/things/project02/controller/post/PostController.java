package com.things.project02.controller.post;

import com.things.project02.domain.Post;
import com.things.project02.dto.PostDto;
import com.things.project02.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 리스트
    @GetMapping("/post")
    public ModelAndView posts(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostDto.PostRes> posts = postService.findAll(pageable);

        ModelAndView modelAndView = new ModelAndView("/post/posts");
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("pageable",pageable);
        return modelAndView;
    }

    // 게시글 작성
    @GetMapping("/post/create")
    public String create() {
        return "/post/save";
    }

    // 게시글 단일 조회
    @GetMapping("/post/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PostDto.PostRes postRes = postService.findById(id);
        model.addAttribute("post", postRes);
        return "/post/detail";
    }

    // 게시글 수정 페이지
    @GetMapping("/post/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        PostDto.PostRes postRes = postService.findById(id);
        model.addAttribute("post", postRes);
        return "/post/save";
    }

}
