package com.things.project02.dto;

import com.things.project02.domain.Post;
import com.things.project02.domain.User;
import lombok.*;

import java.time.format.DateTimeFormatter;

public class PostDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostReq {
        private Long            id;
        private String          title;
        private String          content;
        private UserDto.UserReq userReq;

        @Builder
        public PostReq(String title, String content, UserDto.UserReq userReq) {
            this.title = title;
            this.content = content;
            this.userReq = userReq;
        }

        public Post toEntity() {
            return Post.builder()
                    .title(this.title)
                    .content(this.content)
                    .user(this.userReq.toEntity())
                    .build();
        }

        public Post toEntity(User user) {
            return Post.builder()
                    .title(this.title)
                    .content(this.content)
                    .user(user)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostRes {
        private Long   id;
        private String title;
        private String content;
        private Long   user_id;
        private String userNick;
        private String createdDate;
        private String updatedDate;

        public PostRes(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.user_id = post.getUser().getId();
            this.userNick = post.getUser().getUserNick();
            this.createdDate = post.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            this.updatedDate = post.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }
    }
}
