package com.things.project02.config;

import com.things.project02.repository.post.PostRepository;
import com.things.project02.repository.user.UserRepository;
import com.things.project02.service.post.PostService;
import com.things.project02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final UserRepository       userRepository;
    private final PostRepository       postRepository;

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository, userRepository);
    }
}
