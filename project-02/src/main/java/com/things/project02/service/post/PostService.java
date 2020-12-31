package com.things.project02.service.post;

import com.things.project02.domain.Post;
import com.things.project02.domain.User;
import com.things.project02.dto.PostDto;
import com.things.project02.dto.UserDto;
import com.things.project02.repository.post.PostRepository;
import com.things.project02.repository.user.UserRepository;
import com.things.project02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 게시물 작성
    public Long createPost(PostDto.PostReq postReq, UserDto.UserRes userRes) {
        User user = userRepository.findById(userRes.getId()).orElse(null);
        assert user != null;
        user.addPost(postReq.toEntity(user));
        userRepository.save(user);
        return user.getPosts().get(user.getPosts().size() - 1).getId();
    }

    // 게시물 단일 조회
    public PostDto.PostRes findById(Long id) {
        return postRepository.findById(id).map(PostDto.PostRes::new).orElse(null);
    }

    // 게시물 전체 조회
    public Page<PostDto.PostRes> findAll(int page) {
        int postSize = 10;
        page = (page == 0) ? 0 : (page - 1);
        Pageable pageable = PageRequest.of(page, postSize, Sort.Direction.DESC, "id");
        return postRepository.findAll(pageable).map(PostDto.PostRes::new);
    }

    // 게시물 업데이트
    public void updatePost(PostDto.PostReq postReq, UserDto.UserRes userRes) {
        Post post = postRepository.findPostById(postReq.getId());
        post.updatePost(postReq.toEntity(new UserDto.UserReq(userRes).toEntity()));
        postRepository.flush();
    }
}

