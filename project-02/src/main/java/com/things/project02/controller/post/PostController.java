package com.things.project02.controller.post;

import com.things.project02.dto.PostDto;
import com.things.project02.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 리스트
    @GetMapping("/post/{page}")
    public String posts(@PathVariable int page, Model model) {
        // 한 페이지에 로딩 될 게시글 목록
        page = (page == 0) ? 0 : (page - 1);
        Pageable              pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id");
        Page<PostDto.PostRes> posts    = postService.findAll(pageable);
        int                   next     = posts.hasNext() ? posts.nextPageable().getPageNumber() : 0;
        int                   prev     = posts.hasPrevious() ? posts.previousPageable().getPageNumber() : 0;
        System.out.println("prev = " + prev + " / next = " + next);
        model.addAttribute("posts", posts);

        // 페이지 블럭
        long start     = (long) (page - 1) / posts.getSize() * posts.getSize() + 1;
        long end       = start + posts.getSize() - 1;
        long blockSize = (posts.getTotalElements() + posts.getSize() - 1) / posts.getSize();
        end = Math.min(blockSize, end);

        List<Long> block = new ArrayList<>();
        for (long i = start; i <= end; i++) block.add(i);
        model.addAttribute("block", block);
        model.addAttribute("blockSize", blockSize);
        return "/post/posts";
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
