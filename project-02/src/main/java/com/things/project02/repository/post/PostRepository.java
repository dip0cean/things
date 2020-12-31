package com.things.project02.repository.post;

import com.things.project02.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Post findPostById(Long id);
}
